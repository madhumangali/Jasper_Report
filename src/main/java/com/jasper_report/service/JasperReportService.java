package com.jasper_report.service;

import com.jasper_report.dto.MultiTableColumnsResult;
import net.sf.jasperreports.engine.JRException;

public interface JasperReportService {

    String getReport(boolean recentStyle, MultiTableColumnsResult multiTableColumnsResult) throws JRException, ClassNotFoundException;
}
