package com.jasper_report.service;

import com.jasper_report.dto.MultiTableColumnsResult;
import com.jasper_report.dto.MultipleDataSourceStaticReport;
import com.jasper_report.dto.StyleBuilderParamsDto;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface JasperReportService {

//    String getReport(boolean recentStyle, MultiTableColumnsResult multiTableColumnsResult) throws JRException, ClassNotFoundException;

//    String multipleReports(boolean recentStyle, MultiTableColumnsResult multiTableColumnsResult) throws JRException, ClassNotFoundException;

//    String multipleDataResource(MultipleDataSourceStaticReport multipleDataSourceStaticReport) throws JRException;

    JasperPrint generateJasperReport(List<Map> list, Set set,int topMargin) throws JRException;
}
