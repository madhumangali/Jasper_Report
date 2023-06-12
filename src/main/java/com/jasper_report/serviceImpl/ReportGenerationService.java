package com.jasper_report.serviceImpl;

import com.jasper_report.repository.dummy.KrProductionReportRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

//@Service
@Log4j2
@Service
@NoArgsConstructor
@AllArgsConstructor
public class ReportGenerationService
//        implements Callable<JasperPrint>
        {

//    @Autowired
    private long min;

//    @Autowired
    private long max;

    @Autowired
    private KrProductionReportRepository krProductionReportRepository;

//    public ReportGenerationService(long min, long max) {
//        this.min = min;
//        this.max = max;
//    }

    public String generateStaticReport(
//            long min, long max
            List list
    ) throws FileNotFoundException, JRException, SQLException {

        String filePath = ResourceUtils.getFile("classpath:Blank_A4.jrxml").getAbsolutePath() ;

//        List list=krProductionReportRepository.treatmentDetails(min,max);

        JRBeanCollectionDataSource dataSource=new JRBeanCollectionDataSource(list);

        if(list.isEmpty()){
            log.info("list is  empty");
        }

        Map<String, Object> parameterMap = new HashMap();
        parameterMap.put("min",1L);
        parameterMap.put("max",1000L);
        parameterMap.put("DataSourceCollection",dataSource);

        JasperReport jasperReport = JasperCompileManager.compileReport(
//                filePath
                "C:\\Users\\Administrator\\JaspersoftWorkspace\\MyReports\\Jsw_Report\\Header_Columns\\Jpa_Report.jrxml"
        );
        log.info("Compilation complete");
//        JRDataset[] jrDatasets=jasperReport.getDatasets();
//        System.out.println(jrDatasets.length);
//
//        Arrays.stream(jrDatasets).forEach(jrDataset -> {
//            Arrays.stream(jrDataset.getFields()).forEach(jrField -> {
//                System.out.println(jrField.getName());
//            });
//        });




//        Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/JasperReport","postgres","0367");

        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameterMap, new JREmptyDataSource());
        log.info("Print Completed");


//        File file=new File("D://New");
//        file.canWrite();
//
//        FileWriter fileWriter= new FileWriter(file);

//        JasperPrintManager.printReport( jasperPrint, true);
//        fileWriter.write(String.valueOf(jasperPrint));

        JasperExportManager.exportReportToPdfFile(jasperPrint,"D:\\Reports\\"+"Madhu"+".pdf");
        log.info("Pdf Generated");

        return "Pdf Generated";

    }

//    @Override
//    public JasperPrint call() throws Exception {
//
//
//        String filePath = ResourceUtils.getFile("classpath:Other_details.jrxml").getAbsolutePath() ;
//
//
//
//        Map<String, Object> parameterMap = new HashMap();
//        parameterMap.put("min",min);
//        parameterMap.put("max",max);
//
//        JasperReport jasperReport = JasperCompileManager.compileReport(filePath);
//        log.info("Complilation complete");
//
//        Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/JasperReport","postgres","0367");
//
//        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameterMap, conn);
//
////        JasperExportManager.exportReportToPdfFile(jasperPrint,"D:\\New\\"+Thread.currentThread().getName()+".pdf");
//        log.info("Pdf Generated");
//        return jasperPrint;
//    }
}
