package com.jasper_report.serviceImpl;


import com.jasper_report.configuration.PrimaryConfiguration;
import com.jasper_report.configuration.SecondaryConfiguration;
import com.jasper_report.dto.*;
import com.jasper_report.mapper.EntityMapper;
import com.jasper_report.mapper.EntityPropertyMapper;
import com.jasper_report.mapper.MultiTableColumnsResultMapper;
import com.jasper_report.mapper.StyleBuilderMapper;
//import com.jasper_report.repository.secondary.SecondaryCommonRepository;
//import com.jasper_report.repository.secondary.SecondaryCommonRepository;
import com.jasper_report.repository.primary.PrimaryCommonRepository;
import com.jasper_report.repository.secondary.SecondaryCommonRepository;
import com.jasper_report.repository.primary.EmployeeRepository;
import com.jasper_report.service.EmployeeService;
import com.jasper_report.service.JasperReportService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.type.OrientationEnum;
import net.sf.jasperreports.view.JasperViewer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.PropertySource;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.persistenceunit.DefaultPersistenceUnitManager;
import org.springframework.orm.jpa.persistenceunit.PersistenceUnitManager;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.sql.*;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * This EmployeeServiceImpl Class consists of several Methods which helps to generate Jasper_Report
 */
@Service
@PropertySource(value={"classpath:application.properties"})
@Log4j2
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private StyleBuilderMapper styleBuilderMapper;

    @Autowired
    private JasperReportService jasperReportService;

    @Autowired
    private PrimaryConfiguration primaryConfiguration;

   @Autowired
    private SecondaryConfiguration secondaryConfiguration;

   @Autowired
   private SecondaryCommonRepository secondaryCommonRepository ;

    @Autowired
    private PrimaryCommonRepository primaryCommonRepository;

    @Autowired
    private EntityPropertyMapper entityPropertyMapper;

    @Autowired
    private EntityMapper entityMapper;

//    @Autowired
//    @Qualifier("entityManagerFactory")
//    private EntityManager entityManager;

    @Autowired
    private CommonService commonService;

    @Autowired
    private MultiTableColumnsResultMapper multiTableColumnsResultMapper;

    /**
     * This Method returns the schemas of a database
     */
    @Override
    public List<Schemas> getSchemas() throws SQLException {

        List<Schemas> schemasList=new ArrayList<>();

        Schemas schemaOne=new Schemas();
        schemaOne.setSchemaName(secondaryCommonRepository.findSchemas());
        schemaOne.setCatalogName(secondaryCommonRepository.findCatalogName());
        schemasList.add(schemaOne);

        Schemas schemaTwo=new Schemas();
        schemaTwo.setSchemaName(primaryCommonRepository.findSchemas());
        schemaTwo.setCatalogName(primaryCommonRepository.findCatalogName());
        schemasList.add(schemaTwo);

        log.info("The schemas are fetched from database");

        return schemasList;
    }

    @Override
    public String getDataOfTable(List<String> tableNamesList) throws SQLException, JRException {

        List<JasperPrint> jasperPrints= new ArrayList<>();

        AtomicInteger tableNamesListCount= new AtomicInteger(0);

        tableNamesList.stream().forEach(tableName ->{

                jasperPrints.add(getDataAndReport.apply(tableName,250*tableNamesListCount.getAndIncrement() ));

            System.out.println(jasperPrints.get(tableNamesListCount.get()-1).getBottomMargin());


        });

        List<JasperPrint> partialJasperPrintList=new ArrayList<>();

        AtomicInteger inc=new AtomicInteger(0);
        AtomicInteger jasperPrintInc=new AtomicInteger(1);

        for (JasperPrint jasperPrint: jasperPrints ) {

            JasperPrint partialJasperPrint=new JasperPrint();

            if(inc.get() == 0){
                 partialJasperPrint = jasperPrints.get(jasperPrintInc.get());
            }
            else {
                partialJasperPrint = partialJasperPrintList.get(inc.get()-1);
            }

            if (inc.get() % 2 != 0 && inc.get()>0) {
                continue;
            } else {

                JRPrintPage page1 = partialJasperPrint.getPages().get(0);

                List<JRPrintPage> pages2 = jasperPrint.getPages();
                for (JRPrintPage page : pages2) {
                    List<JRPrintElement> elements = page.getElements();
                    for (JRPrintElement element : elements) {
                        page1.addElement(element);
                    }
                }

                JasperViewer.viewReport(partialJasperPrint,false);

                partialJasperPrintList.add(jasperPrint);

            }

            inc.getAndIncrement();

        }

        int size= partialJasperPrintList.size();
//        JasperViewer.viewReport(partialJasperPrintList.get(size-1),false);

        String filePath = "D:\\New\\jsw.pdf";

//        JasperExportManager.exportReportToPdfFile(partialJasperPrintList.get(size-1),filePath);

        return  filePath;

    }

    BiFunction<String, Integer,JasperPrint> getDataAndReport= (tableName, topMargin) ->{

        try {
            return jasperReport(commonService.primaryDatabaseFunction.apply(tableName),topMargin);
        } catch (Exception e) {
            try {
                return jasperReport(commonService.secondaryDatabaseFunction.apply(tableName),topMargin);
            } catch (JRException ex) {
                throw new RuntimeException(ex);
            }
        }

    };

    public JasperPrint jasperReport(List<Map> list,int topMargin) throws JRException {

       Set headers= list.get(0).keySet();

       JasperPrint jasperPrint=jasperReportService.generateJasperReport(list,headers,topMargin);

        JasperViewer.viewReport(jasperPrint,false);

//        JasperPrint print= new JasperPrint();
//        print.

        return  jasperPrint;
    }


}


