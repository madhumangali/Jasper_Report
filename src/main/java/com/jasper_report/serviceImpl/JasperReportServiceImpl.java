package com.jasper_report.serviceImpl;

import ar.com.fdvs.dj.core.DynamicJasperHelper;
import ar.com.fdvs.dj.core.layout.ClassicLayoutManager;
import ar.com.fdvs.dj.domain.*;
import ar.com.fdvs.dj.domain.builders.ColumnBuilder;
import ar.com.fdvs.dj.domain.builders.ColumnBuilderException;
import ar.com.fdvs.dj.domain.builders.DynamicReportBuilder;
import ar.com.fdvs.dj.domain.builders.StyleBuilder;
import ar.com.fdvs.dj.domain.constants.*;
import ar.com.fdvs.dj.domain.constants.Font;
import ar.com.fdvs.dj.domain.constants.Transparency;
import ar.com.fdvs.dj.domain.entities.columns.AbstractColumn;
import com.jasper_report.dto.MultiTableColumnsResult;
import com.jasper_report.dto.StyleBuilderParamsDto;
import com.jasper_report.exception.EmployeeException;
import com.jasper_report.mapper.StyleBuilderMapper;
import com.jasper_report.model.Enum.PdfTitle;
import com.jasper_report.model.StyleBuilderDuplicate;
import com.jasper_report.model.StyleBuilderParams;
import com.jasper_report.repository.StyleBuilderDuplicateRepository;
import com.jasper_report.repository.StyleBuilderRepository;
import com.jasper_report.service.JasperReportService;
import lombok.extern.log4j.Log4j2;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;

/**
 * This JasperReportServiceImpl Class consists of several Methods which helps to generate Jasper_Report
 */
@Service
@Log4j2
public class JasperReportServiceImpl implements JasperReportService {

    @Autowired
    private StyleBuilderMapper styleBuilderMapper ;

    @Autowired
    private StyleBuilderRepository styleBuilderRepository;

    @Autowired
    private StyleBuilderDuplicateRepository styleBuilderDuplicateRepository;


    StyleBuilderParamsDto headerStyle = null,columnStyle=null,titleStyle=null,subTitleStyle=null;

    /**
     * Input params (recentStyle, multiTableColumnsResult)
     * Returns Path where JasperReport Generated
     */
    public String getReport(boolean recentStyle, MultiTableColumnsResult multiTableColumnsResult) throws ColumnBuilderException,  JRException, ClassNotFoundException {

        if(multiTableColumnsResult.getFileName() == null)
        {
            log.error("File Name should not be null/missed");
            throw  new EmployeeException("File Name should not be null/missed");
        }

        /**
         * Setting jasper_report file Name by using reference variableName ( multiTableColumnsResult.getFileName() )
         * Generating filePath where the report has to be Generated
         */
        String filePath = "D:\\New\\"+multiTableColumnsResult.getFileName()+".pdf";

        /**
         * Calling Method(getJasperReport) and will get DynamicReport in return
         */
        DynamicReport dynaReport = getJasperReport(recentStyle,multiTableColumnsResult);

        /**
         * Calling Method(generateJasperPrint) by passing input variables(dynamicReport,ClassicLayoutManager Object,
         * dataSource of selected columns )
         * This Method(generateJasperPrint) will print the data in the dynamic Report
         * Calling Method(exportReportToPdfFile) by passing input variables(JasperPrint, filePath)
         * This Method(exportReportToPdfFile) will convert dynamic report to pdf and generate Pdf in required path(filePath)
         */
        JasperPrint jp = DynamicJasperHelper.generateJasperPrint(dynaReport, new ClassicLayoutManager(),
                new JRBeanCollectionDataSource(multiTableColumnsResult.getRows()));
        JasperExportManager.exportReportToPdfFile(jp, filePath);

        /**
         * if we don't want the previous Styling's of pdf than we are deleting the entire data in the table (StyleBuilderDuplicate)
         */
        if(recentStyle == false) {
            log.info("The entire data was deleted from the table : StyleBuilderDuplicate");
            styleBuilderDuplicateRepository.deleteAll();
        }

        /**
         * Iterating the StyleBuilderParamsList
         * storing the data of StyleBuilderParamsList in table(styleBuilderParams)
         * if we are not using the previous Styling's of pdf than ,
         * we are storing data of  the entire data of StyleBuilderParamsList in the table (StyleBuilderDuplicate)
         */
        multiTableColumnsResult.getStyleBuilderParamsList().stream().forEach(param ->{

           StyleBuilderParams styleBuilderParams =styleBuilderMapper.mapToBuilderParams(param,multiTableColumnsResult.getTitleName(),
                   multiTableColumnsResult.getSubtitleName(),multiTableColumnsResult.getFileName(),multiTableColumnsResult.getReportHeader(),
                   multiTableColumnsResult.getDescription(),multiTableColumnsResult.getSubject());
            styleBuilderParams.setDate(Date.valueOf(LocalDate.now()));
            styleBuilderParams.setTime(Time.valueOf(LocalTime.now()));
            styleBuilderRepository.save(styleBuilderParams);

            log.info("The styling's are saved in table : StyleBuilderParams ");

            if(recentStyle == false) {
                StyleBuilderDuplicate styleBuilderDuplicate = styleBuilderMapper.mapTODuplicate(param, multiTableColumnsResult.getTitleName(),
                        multiTableColumnsResult.getSubtitleName(), multiTableColumnsResult.getFileName(), multiTableColumnsResult.getReportHeader(),
                        multiTableColumnsResult.getDescription(), multiTableColumnsResult.getSubject());
                styleBuilderDuplicate.setDate(Date.valueOf(LocalDate.now()));
                styleBuilderDuplicate.setTime(Time.valueOf(LocalTime.now()));
                styleBuilderDuplicateRepository.save(styleBuilderDuplicate);
                log.info("The styling's are saved in table : StyleBuilderDuplicate ");
            }

        });

        log.info("The jasper_report is generated");
        return "The Pdf Generated Path : "+filePath;
    }

    /**
     * FunctionInterface(createStyle) uses to create Style parameter uses for styling the DynamicReport
     * input Variable(StyleBuilderParamsDto)
     * Returns variable(Style)
     * This FunctionInterface(createStyle) uses for styling the Variables(Header,Column,Title,SubTitle)
     * calling Method(stylingProcedure) uses for setting the styleBuilderParams fields in suitable Style fields
     */
    Function<StyleBuilderParamsDto,Style> createStyle = (styleBuilderParams) ->{
       Style sb = new Style();

        try {
            sb=stylingProcedure(styleBuilderParams,sb);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        log.info("The Styling's are formed for : "+styleBuilderParams.getPdfTitle());

        return sb;
    };

    /**
     * FunctionInterface(createStyle) uses to create Style parameter uses for styling the DynamicReport
     * input Variable(StyleBuilderParamsDto)
     * Returns variable(Style)
     * This FunctionInterface(createStyle) uses only for styling the Variables(reportHeader, description)
     */
    Function<StyleBuilderParamsDto,Style> reportHeaderStyle = (styleBuilderParams) ->{

        StyleBuilder sb = new StyleBuilder(true);
//        sb.setPaddingTop(10);
        sb.setHorizontalAlign(HorizontalAlign.CENTER);
        sb.setVerticalAlign(VerticalAlign.MIDDLE);
        sb.setBorderBottom(Border.PEN_1_POINT());
        sb.setBorderColor(Color.BLACK);
        sb.setFont(Font.ARIAL_BIG_BOLD);
        sb.setTextColor(Color.blue);
        sb.setBackgroundColor(Color.PINK);

        log.info("The Styling's are formed for : "+styleBuilderParams.getPdfTitle());

        return sb.build();
    };

    /**
     * The Method(getJasperReport) uses for the creation of Document(DynamicReport)
     * Input Variables(recentStyle,multiTableColumnsResult)
     * Returns Document(DynamicReport)
     */
    private DynamicReport getJasperReport(boolean recentStyle,MultiTableColumnsResult multiTableColumnsResult) throws ColumnBuilderException, ClassNotFoundException {

        DynamicReportBuilder report = new DynamicReportBuilder();

        /**
         * if we want Previous Styling's of pdf ('i.e' recentStyle = true)
         * Fetching the latest Styling's data from the table (styleBuilderDuplicate)
         * if data is not present the Exception throws here
         */
        if(recentStyle == true){

            List<StyleBuilderDuplicate> styleBuilderDuplicateList=styleBuilderDuplicateRepository.findAll();

            if(styleBuilderDuplicateList.isEmpty()){
                log.error("Previous style is not found in database , pls add styling manually");
                throw new EmployeeException("Previous style is not found in database , pls add styling manually");
            }

            List<StyleBuilderParamsDto> styleBuilderParamsDtoList=new ArrayList<>();

            /**
             * Iterating the fetched data from the table (styleBuilderDuplicate)
             * Setting the data in the variable(multiTableColumnsResult) in our required form
             */
            styleBuilderDuplicateList.stream().forEach(styleBuilderDuplicate -> {

                multiTableColumnsResult.setTitleName(styleBuilderDuplicate.getTitleName());
                multiTableColumnsResult.setSubtitleName(styleBuilderDuplicate.getSubtitleName());

                styleBuilderParamsDtoList.add(styleBuilderMapper.mapToDto(styleBuilderDuplicate));

                log.info("Previous Styling from table : StyleBuilderDuplicate");

            });

            multiTableColumnsResult.setStyleBuilderParamsList(styleBuilderParamsDtoList);

        }

        Map map = multiTableColumnsResult.getRows().get(1);

        if(multiTableColumnsResult.getStyleBuilderParamsList().isEmpty()){
            log.error("jasper report styling params are not passed");
            throw new EmployeeException("jasper report styling params are not passed");
        }

        /**
         * Here the separating the styling's of  pdf based on PdfTitle
         * Storing the separated style's in variables(headerStyle,columnStyle,titleStyle,subTitleStyle)
         */
        multiTableColumnsResult.getStyleBuilderParamsList().stream().forEach(style ->{
            if(style.getPdfTitle().equals(PdfTitle.HEADER)) {
                headerStyle = style;
            } else if (style.getPdfTitle().equals(PdfTitle.COLUMN)) {
                columnStyle = style;
            } else if (style.getPdfTitle().equals(PdfTitle.TITLE)) {
                titleStyle=style;
            }else {
                subTitleStyle=style;
            }
        });
        
        AtomicInteger indexTwo=new AtomicInteger(0);

        /**
         * Adding Headers (Styling's and Properties) and Columns Styling to the document(DynamicReport)
         * Adding the Others required fields to document(DynamicReport)
         */
        multiTableColumnsResult.getColumnHeaders().stream().forEach(columnHeader ->{

            AbstractColumn columnState = ColumnBuilder.getInstance()
                    .setColumnProperty(multiTableColumnsResult.getColumnHeaders().get(indexTwo.get()),
                            map.get(multiTableColumnsResult.getColumnHeaders().get(indexTwo.get())).getClass().getName())
                    .setTitle(multiTableColumnsResult.getColumnHeaders().get(indexTwo.getAndIncrement())).setWidth(50)
                    .setStyle(createStyle.apply(columnStyle)).setHeaderStyle(createStyle.apply(headerStyle)).build();

            report.addColumn(columnState);
        });

        log.info("The column Headers and its Properties are added to report");

        report.setTitle(multiTableColumnsResult.getTitleName()+ " :-");
        report.setTitleStyle(createStyle.apply(titleStyle));
//        report.setSubtitle(multiTableColumnsResult.getSubtitleName());
//        report.setSubtitleStyle(createStyle.apply(subTitleStyle));
        report.setReportName(multiTableColumnsResult.getReportHeader());

        Style style=new Style();
        style.setBackgroundColor(Color.PINK);
        report.setOddRowBackgroundStyle(style);
        report.setPrintBackgroundOnOddRows(true);
        report.setMargins(20,20,20,20);

//        report.setPageSizeAndOrientation(new Page(1000,1000));

        report.addAutoText(multiTableColumnsResult.getReportHeader(), AutoText.POSITION_HEADER,
                AutoText.ALIGNMENT_CENTER,AutoText.DEFAULT_WIDTH,reportHeaderStyle.apply(columnStyle));
        report.addAutoText(multiTableColumnsResult.getDescription(), AutoText.POSITION_HEADER,
                AutoText.ALIGNMENT_CENTER,200,reportHeaderStyle.apply(columnStyle));
        report.setUseFullPageWidth(true);
        report.addImageBanner("D:\\New\\Jsw_Steel.png",50,50, ImageBanner.Alignment.Right, ImageScaleMode.FILL_PROPORTIONALLY);
        log.info("All the Properties are added to report");
        return report.build();
    }

    /**
     *The method(stylingProcedure) uses for setting the styleBuilderParams fields in suitable Style fields
     * Input Params(styleBuilderParams,Style)
     * Return Style
     */
    public Style stylingProcedure(StyleBuilderParamsDto styleBuilderParams, Style sb) throws NoSuchFieldException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {

        Field fieldFont=Font.class.getDeclaredField(styleBuilderParams.getFontStyles().toString());
        sb.setFont((Font) fieldFont.get(styleBuilderParams.getFontStyles().toString()));

        Method method = Border.class.getDeclaredMethod(styleBuilderParams.getBorderStyle().toString());
        sb.setBorder((Border) method.invoke(null,null));

        Field fieldBorder=Color.class.getDeclaredField(styleBuilderParams.getBorderColor().toString());
        sb.setBorderColor((Color) fieldBorder.get(styleBuilderParams.getBorderColor().toString()));

        Field fieldText=Color.class.getDeclaredField(styleBuilderParams.getTextColor().toString());
        sb.setTextColor((Color) fieldText.get(styleBuilderParams.getTextColor().toString()));

        Field fieldHorizontalAlign=HorizontalAlign.class.getDeclaredField(styleBuilderParams.getHorizontalAlignmentEnum().toString());
        sb.setHorizontalAlign((HorizontalAlign) fieldHorizontalAlign.get(styleBuilderParams.getHorizontalAlignmentEnum().toString()));

        Field fieldVerticalAlign=VerticalAlign.class.getDeclaredField(styleBuilderParams.getVerticalAlignmentEnum().toString());
        sb.setVerticalAlign((VerticalAlign) fieldVerticalAlign.get(styleBuilderParams.getVerticalAlignmentEnum().toString()));

        Field fieldBackGround=Color.class.getDeclaredField(styleBuilderParams.getBackgroundColor().toString());
        sb.setBackgroundColor((Color) fieldBackGround.get(styleBuilderParams.getBackgroundColor().toString()));

        sb.setTransparency(Transparency.OPAQUE);

        log.info("All the styling properties are added to title : "+styleBuilderParams.getPdfTitle());
        return sb;
    }
}
