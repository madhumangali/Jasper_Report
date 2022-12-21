package com.jasper_report.serviceImpl;

import ar.com.fdvs.dj.core.DynamicJasperHelper;
import ar.com.fdvs.dj.core.layout.ClassicLayoutManager;
import ar.com.fdvs.dj.domain.*;
import ar.com.fdvs.dj.domain.builders.ColumnBuilder;
import ar.com.fdvs.dj.domain.builders.ColumnBuilderException;
import ar.com.fdvs.dj.domain.builders.DynamicReportBuilder;
import ar.com.fdvs.dj.domain.constants.*;
import ar.com.fdvs.dj.domain.constants.Font;
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

    public String getReport(boolean recentStyle, MultiTableColumnsResult multiTableColumnsResult) throws ColumnBuilderException,  JRException, ClassNotFoundException {

        if(multiTableColumnsResult.getFileName() == null)
        {
            log.error("File Name should not be null/missed");
            throw  new EmployeeException("File Name should not be null/missed");
        }

        String filePath = "D:\\New\\"+multiTableColumnsResult.getFileName()+".pdf";

        DynamicReport dynaReport = getJasperReport(recentStyle,multiTableColumnsResult);

        JasperPrint jp = DynamicJasperHelper.generateJasperPrint(dynaReport, new ClassicLayoutManager(),
                new JRBeanCollectionDataSource(multiTableColumnsResult.getRows()));
        JasperExportManager.exportReportToPdfFile(jp, filePath);

        if(recentStyle == false) {
            log.info("The entire data was deleted from the table : StyleBuilderDuplicate");
            styleBuilderDuplicateRepository.deleteAll();
        }

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

    Function<StyleBuilderParamsDto,Style> reportHeaderStyle = (styleBuilderParams) ->{

        Style sb = new Style();
        sb.setPaddingTop(10);
        sb.setHorizontalAlign(HorizontalAlign.CENTER);
        sb.setVerticalAlign(VerticalAlign.MIDDLE);
        sb.setBorderBottom(Border.PEN_1_POINT());
        sb.setBorderColor(Color.BLACK);
        sb.setFont(Font.ARIAL_BIG_BOLD);
        sb.setTextColor(Color.blue);
        sb.setBackgroundColor(Color.PINK);

        log.info("The Styling's are formed for : "+styleBuilderParams.getPdfTitle());

        return sb;
    };

    private DynamicReport getJasperReport(boolean recentStyle,MultiTableColumnsResult multiTableColumnsResult) throws ColumnBuilderException, ClassNotFoundException {

        DynamicReportBuilder report = new DynamicReportBuilder();

        if(recentStyle == true){

            List<StyleBuilderDuplicate> styleBuilderDuplicateList=styleBuilderDuplicateRepository.findAll();

            if(styleBuilderDuplicateList.isEmpty()){
                log.error("Previous style is not found in database , pls add styling manually");
                throw new EmployeeException("Previous style is not found in database , pls add styling manually");
            }

            List<StyleBuilderParamsDto> styleBuilderParamsDtoList=new ArrayList<>();

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

        if(columnStyle.getPdfTitle()!= PdfTitle.COLUMN ){
            log.error("styling params are not given for COLUMNS");
            throw new EmployeeException("styling params are not given for COLUMNS");
        } else if (headerStyle.getPdfTitle()!=PdfTitle.HEADER) {
            log.error("styling params are not given for HEADER");
            throw new EmployeeException("styling params are not given for HEADER");
        } else if (titleStyle.getPdfTitle()!=PdfTitle.TITLE) {
            log.error("styling params are not given for TITLE");
            throw new EmployeeException("styling params are not given for TITLE");
        }else if (subTitleStyle.getPdfTitle() !=PdfTitle.SUBTITLE){
            log.error("styling params are not given for SUBTITLE");
            throw new EmployeeException("styling params are not given for SUBTITLE");
        }

        AtomicInteger indexTwo=new AtomicInteger(0);

        multiTableColumnsResult.getColumnHeaders().stream().forEach(columnHeader ->{

            AbstractColumn columnState = ColumnBuilder.getInstance()
                    .setColumnProperty(multiTableColumnsResult.getColumnHeaders().get(indexTwo.get()),
                            map.get(multiTableColumnsResult.getColumnHeaders().get(indexTwo.get())).getClass().getName())
                    .setTitle(multiTableColumnsResult.getColumnHeaders().get(indexTwo.getAndIncrement())).setWidth(50)
                    .setStyle(createStyle.apply(columnStyle)).setHeaderStyle(createStyle.apply(headerStyle)).build();

            report.addColumn(columnState);
        });

        log.info("The column Headers and its Properties are added to report");

        report.setTitle(multiTableColumnsResult.getTitleName());
        report.setTitleStyle(createStyle.apply(titleStyle));
        report.setSubtitle(multiTableColumnsResult.getSubtitleName());
        report.setSubtitleStyle(createStyle.apply(subTitleStyle));
        report.setReportName(multiTableColumnsResult.getReportHeader());

        Style style=new Style();
        style.setBackgroundColor(Color.orange);
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

        if(styleBuilderParams.getPdfTitle().equals(PdfTitle.COLUMN)){
            sb.setBorderTop(Border.PEN_1_POINT());
            sb.setBackgroundColor(Color.orange);
        }

        log.info("All the styling properties are added to title : "+styleBuilderParams.getPdfTitle());
        return sb;
    }
}
