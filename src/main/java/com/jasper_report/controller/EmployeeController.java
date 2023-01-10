package com.jasper_report.controller;


import com.jasper_report.dto.MultiTableColumnsParams;
import com.jasper_report.dto.MultiTableColumnsResult;
import com.jasper_report.dto.MultipleDataSourceStaticReport;
import com.jasper_report.dto.StyleBuilderParamsDto;
import com.jasper_report.service.JasperReportService;
import com.jasper_report.service.EmployeeService;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * This controller consists of several Methods which helps to generate Jasper_Report
 */
@RestController
@RequestMapping("/employee")
@Log4j2
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private JasperReportService jasperReportService;

    @GetMapping(value = "/")
    @Operation(summary = "To fetch list of schemas in a database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of schemas was fetched"),
            @ApiResponse(responseCode = "400", description = "No schemas found at database",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Invalid Database connection",
                    content = @Content) })
    public ResponseEntity<?> getSchemas( ) throws Exception {

        ResponseEntity<?> response = new ResponseEntity<>(employeeService.getSchemas(), HttpStatus.OK);
        log.info("Schemas are fetched successfully for the database");
        return response;

    }

    @GetMapping(value = "/jasperReport")
    public ResponseEntity<?> getDataOfTable(@RequestBody List<String> tableNamesList ) throws Exception {
        ResponseEntity<?> response = new ResponseEntity<>(employeeService.getDataOfTable(tableNamesList), HttpStatus.OK);
        log.info("Schemas are fetched successfully for the database");
        return response;

    }

}
