package com.jasper_report.serviceImpl;

import ar.com.fdvs.dj.core.DynamicJasperHelper;
import ar.com.fdvs.dj.core.layout.ClassicLayoutManager;
import ar.com.fdvs.dj.domain.*;
import ar.com.fdvs.dj.domain.builders.*;
import ar.com.fdvs.dj.domain.constants.*;
import ar.com.fdvs.dj.domain.constants.Font;
import ar.com.fdvs.dj.domain.constants.Transparency;
import ar.com.fdvs.dj.domain.entities.columns.AbstractColumn;
import com.jasper_report.dto.MultiTableColumnsResult;
import com.jasper_report.dto.MultipleDataSourceStaticReport;
import com.jasper_report.dto.StyleBuilderParamsDto;
import com.jasper_report.exception.EmployeeException;
import com.jasper_report.mapper.StyleBuilderMapper;
import com.jasper_report.model.Enum.PdfTitle;
import com.jasper_report.model.domain.StyleBuilderDuplicate;
import com.jasper_report.model.domain.StyleBuilderParams;
import com.jasper_report.repository.primary.StyleBuilderDuplicateRepository;
import com.jasper_report.repository.primary.StyleBuilderRepository;
import com.jasper_report.service.JasperReportService;
import lombok.extern.log4j.Log4j2;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * This JasperReportServiceImpl Class consists of several Methods which helps to generate Jasper_Report
 */
@Service
@Log4j2
public class JasperReportServiceImpl extends DynamicJasperHelper implements JasperReportService {

    /**
     * FunctionInterface(createStyle) uses to create Style parameter uses for styling the DynamicReport
     * input Variable(StyleBuilderParamsDto)
     * Returns variable(Style)
     * This FunctionInterface(createStyle) uses for styling the Variables(Header,Column,Title,SubTitle)
     * calling Method(stylingProcedure) uses for setting the styleBuilderParams fields in suitable Style fields
     */

    Supplier<Style> headerStyle=() ->{

        Style sb = new Style();
        float[ ] bg=Color.RGBtoHSB(0, 0, 128,null);
        sb.setBackgroundColor(Color.getHSBColor(bg[0], bg[1], bg[2]));
        sb.setTextColor(Color.RED);
        sb.setVerticalAlign(VerticalAlign.MIDDLE);
        sb.setFont(Font.ARIAL_BIG);
        sb.setBorderColor(Color.BLACK);
        sb.setBorderBottom(Border.PEN_1_POINT());
        sb.setHorizontalAlign(HorizontalAlign.CENTER);
        sb.setBorder(Border.PEN_2_POINT());
        sb.setTransparency(Transparency.OPAQUE);

        return sb;
    };

    Supplier<Style> columnStyle=() ->{

        Style sb = new Style();

        sb.setBackgroundColor(Color.LIGHT_GRAY);
        sb.setTextColor(Color.BLACK);
        sb.setVerticalAlign(VerticalAlign.MIDDLE);
        sb.setFont(Font.ARIAL_BIG);
        sb.setBorderColor(Color.BLACK);
        sb.setBorderBottom(Border.PEN_1_POINT());
        sb.setHorizontalAlign(HorizontalAlign.CENTER);
        sb.setBorder(Border.PEN_1_POINT());
        sb.setTransparency(Transparency.OPAQUE);

        return sb;
    };

    public JasperPrint generateJasperReport(List<Map> list, Set set,int topMargin) throws JRException {

       Collection properties= list.get(0).values();
       Iterator pr= properties.iterator();

        DynamicReportBuilder  parentReportBuilder=new DynamicReportBuilder();

       AtomicInteger inc=new AtomicInteger(0);
        set.stream().forEach(columnHeader -> {

            AbstractColumn columnState = ColumnBuilder.getInstance()
                    .setColumnProperty(columnHeader.toString(),pr.next().getClass().getName())
                    .setTitle(columnHeader.toString()).setWidth(50)
                    .setStyle(columnStyle.get()).setHeaderStyle(headerStyle.get()).build();

            parentReportBuilder.addColumn(columnState);
        });

        parentReportBuilder.setPrintBackgroundOnOddRows(true);
//        parentReportBuilder.setMargins(20,20,20,20);
//        parentReportBuilder.setPageSizeAndOrientation(new Page(1000,1000));
//        parentReportBuilder.setPageSizeAndOrientation(Page.Page_A4_Portrait());
//        parentReportBuilder.setAllowDetailSplit(true);
//        parentReportBuilder.setUseFullPageWidth(true);
        parentReportBuilder.setColumnsPerPage(1,0);
        parentReportBuilder.setTopMargin(topMargin);


        JasperReport report = DynamicJasperHelper.generateJasperReport(parentReportBuilder.build(), new ClassicLayoutManager(), new HashMap<>());
        JasperPrint jasperPrint=JasperFillManager.fillReport(report,new HashMap<>() ,new JRBeanCollectionDataSource(list));

        return  jasperPrint;
    }

    /**
     * The Method(getJasperReport) uses for the creation of Document(DynamicReport)
     * Input Variables(recentStyle,multiTableColumnsResult)
     * Returns Document(DynamicReport) with Multiple reports
     */
//    @Override
//    public String multipleReports(boolean recentStyle,MultiTableColumnsResult multiTableColumnsResult) throws JRException {
//
//        JasperPrint jasperPrint=new JasperPrint();
//
//
//        List<AbstractColumn> abstractColumns=new ArrayList<>();
//
//
//
//        Map map = multiTableColumnsResult.getRows().get(1);
//
//        /**
//         * Here the separating the styling's of  pdf based on PdfTitle
//         * Storing the separated style's in variables(headerStyle,columnStyle,titleStyle,subTitleStyle)
//         */
//        multiTableColumnsResult.getStyleBuilderParamsList().stream().forEach(style ->{
//            if(style.getPdfTitle().equals(PdfTitle.HEADER)) {
//                headerStyle = style;
//            } else if (style.getPdfTitle().equals(PdfTitle.COLUMN)) {
//                columnStyle = style;
//            } else if (style.getPdfTitle().equals(PdfTitle.TITLE)) {
//                titleStyle=style;
//            }else {
//                subTitleStyle=style;
//            }
//        });
//
//
//
//        /**
//         * Adding Headers (Styling's and Properties) and Columns Styling to the document(DynamicReport)
//         * Adding the Others required fields to document(DynamicReport)
//         */
//        for(int i=0;i<4;i++) {
//
//            System.out.println(i);
//
//            DynamicReportBuilder  parentReportBuilder=new DynamicReportBuilder();
//
//            AtomicInteger indexTwo=new AtomicInteger(0);
//
//
//            multiTableColumnsResult.getColumnHeaders().stream().forEach(columnHeader -> {
//
//                AbstractColumn columnState = ColumnBuilder.getInstance()
//                        .setColumnProperty(multiTableColumnsResult.getColumnHeaders().get(indexTwo.get()),
//                                map.get(multiTableColumnsResult.getColumnHeaders().get(indexTwo.get())).getClass().getName())
//                        .setTitle(multiTableColumnsResult.getColumnHeaders().get(indexTwo.getAndIncrement())).setWidth(50)
//                        .setStyle(createStyle.apply(columnStyle)).setHeaderStyle(createStyle.apply(headerStyle)).build();
//
//                parentReportBuilder.addColumn(columnState);
//            });
//
//            parentReportBuilder.setPrintBackgroundOnOddRows(true);
//            parentReportBuilder.setMargins(20,20,20,20);
//            parentReportBuilder.setPageSizeAndOrientation(new Page(1000,1000));
//            parentReportBuilder.setPageSizeAndOrientation(Page.Page_A4_Portrait());
//            parentReportBuilder.setAllowDetailSplit(true);
//            parentReportBuilder.setUseFullPageWidth(true);
//            parentReportBuilder.setColumnsPerPage(1,0);
//
//            if(i%2 ==0){
//                parentReportBuilder.setLeftMargin(300);
//            }else{
//                parentReportBuilder.setRightMargin(300);
//            }
//            if(i>=2){
//                parentReportBuilder.setTopMargin(250);
//            }
//
//            JasperReport report = DynamicJasperHelper.generateJasperReport(parentReportBuilder.build(), new ClassicLayoutManager(), new HashMap<>());
//            JasperPrint partialJasperPrint=JasperFillManager.fillReport(report,new HashMap<>() ,new JRBeanCollectionDataSource(multiTableColumnsResult.getRows()));
//
//            JasperViewer.viewReport(partialJasperPrint,false);
//
//            if(i>0){
//                JRPrintPage page1 = partialJasperPrint.getPages().get(0);
//                List<JRPrintPage> pages2 = jasperPrint.getPages();
//                for (JRPrintPage page : pages2) {
//                    List<JRPrintElement> elements = page.getElements();
//                    for (JRPrintElement element : elements) {
//                        page1.addElement(element);
//                    }
//                }
//            }
//
//                jasperPrint = partialJasperPrint;
//
//        }
//
//        log.info("The column Headers and its Properties are added to report");
//
//        String filePath = "D:\\New\\"+multiTableColumnsResult.getFileName()+".pdf";
//
//        JasperViewer.viewReport(jasperPrint,false);
//
//        JasperExportManager.exportReportToPdfFile(jasperPrint,filePath);
//
//        return filePath;
//
//    }



}
