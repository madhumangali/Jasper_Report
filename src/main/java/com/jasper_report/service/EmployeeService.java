package com.jasper_report.service;


import com.jasper_report.dto.*;
import net.sf.jasperreports.engine.JRException;

import java.sql.SQLException;
import java.util.List;

public interface EmployeeService {
    Entities getTablesList(String schemaName);

    Entity getTableColumnsAndChildTables(String schemaName,String tableName) throws SQLException;

    List<Entity> getMultipleTableColumnsAndChildTables(String schemaName,List<String> tableNames);

    String getJasperReport(boolean recentStyle,MultiTableColumnsParams multiTableColumnsParams) throws JRException, ClassNotFoundException;

    Schemas getSchemas();
}
