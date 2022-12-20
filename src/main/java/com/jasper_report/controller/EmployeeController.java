package com.jasper_report.controller;


import com.jasper_report.dto.MultiTableColumnsParams;
import com.jasper_report.service.JasperReportService;
import com.jasper_report.service.EmployeeService;


import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/employee")
@Log4j2
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private JasperReportService jasperReportService;

    @GetMapping(value = "/{schemaName}/entityList")
    public ResponseEntity<?> getTablesList(@RequestParam String schemaName) throws Exception {

        ResponseEntity<?> response = new ResponseEntity<>(employeeService.getTablesList(schemaName), HttpStatus.OK);
        log.info("Table Data fetched successfully");
        return response;

    }

    @GetMapping(value = "/{schemaName}/{tableName}")
    public ResponseEntity<?> getTableColumnsAndChildTables(@RequestParam String schemaName,@RequestParam String tableName) throws Exception {

        ResponseEntity<?> response = new ResponseEntity<>(employeeService.getTableColumnsAndChildTables(schemaName,tableName), HttpStatus.OK);
        log.info("Table columns Data fetched successfully");
        return response;

    }

    @GetMapping(value = "/{recentStyle}")
    public ResponseEntity<?> getJasperReport(@RequestParam boolean recentStyle,@RequestBody MultiTableColumnsParams multiTableColumnsParams) throws Exception {

        ResponseEntity<?> response = new ResponseEntity<>(employeeService.getJasperReport(recentStyle,multiTableColumnsParams), HttpStatus.OK);
        log.info("Table columns Data fetched successfully");
        return response;

    }

    @GetMapping(value = "/{schemaName}")
    public ResponseEntity<?> getMultipleTableColumnsAndChildTables(@RequestParam String schemaName, @RequestBody List<String> tableNames) throws Exception {

        ResponseEntity<?> response = new ResponseEntity<>(employeeService.getMultipleTableColumnsAndChildTables(schemaName,tableNames),
                HttpStatus.OK);
        log.info("Multiple Table columns Data fetched successfully");
        return response;

    }

    @GetMapping(value = "/")
    public ResponseEntity<?> getSchemas() throws Exception {

        ResponseEntity<?> response = new ResponseEntity<>(employeeService.getSchemas(), HttpStatus.OK);
        log.info("Multiple Table columns Data fetched successfully");
        return response;

    }

}
