package com.jasper_report.controller;


import com.jasper_report.dto.MultiTableColumnsParams;
import com.jasper_report.dto.MultiTableColumnsResult;
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

    /**
     *This controller Method is used to fetch the list of tables in a particular schema
     */
    @GetMapping(value = "/{schemaName}/entityList")
    @Operation(summary = "To fetch list of tables in a particular schema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the Tables"),
            @ApiResponse(responseCode = "400", description = "Invalid Schema",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Tables are not found in the mentioned schema",
                    content = @Content) })
    public ResponseEntity<?> getTablesList(@Parameter(description = "schemaName of database from which tables has to fetch") @RequestParam String schemaName) throws Exception {

        ResponseEntity<?> response = new ResponseEntity<>(employeeService.getTablesList(schemaName), HttpStatus.OK);
        log.info("All the tables are fetched from schema :"+ schemaName);
        return response;

    }

    /**
     *This controller Method is used to fetch the list of columns and child Tables in a particular Table of a particular schema
     */
    @GetMapping(value = "/{schemaName}/{tableName}")
    @Operation(summary = "To fetch Entity Properties and child Tables in a particular schema of a particular Entity")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the Entity Properties and Child Tables"),
            @ApiResponse(responseCode = "400", description = "Invalid Entity/Table",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Not Found the Entity Properties and Child Tables",
                    content = @Content) })
    public ResponseEntity<?> getTableColumnsAndChildTables(@Parameter(description = "schemaName of database")@RequestParam String schemaName,
                                                           @Parameter(description = "tableName of schema ")@RequestParam String tableName) throws Exception {

        ResponseEntity<?> response = new ResponseEntity<>(employeeService.getTableColumnsAndChildTables(schemaName,tableName), HttpStatus.OK);
        log.info("Table Columns and child Tables Data fetched successfully for the table : "+tableName);
        return response;

    }

    /**
     *This controller Method is used to fetch the data of selected columns from the database
     * And also generates Jasper_Report with this data
     */
    @GetMapping(value = "/{recentStyle}")
    @Operation(summary = "To generate Jasper_report for the selected columns")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Jasper_Report is Generated"),
            @ApiResponse(responseCode = "400", description = "Jasper_Report is not Generated",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Jasper_Report is not Generated",
                    content = @Content) })
    public ResponseEntity<?> getJasperReport(@Parameter(description = "Previous Styling's of report") @RequestParam boolean recentStyle,
                                             @RequestBody MultiTableColumnsParams multiTableColumnsParams) throws Exception {

        ResponseEntity<?> response = new ResponseEntity<>(employeeService.getJasperReport(recentStyle,multiTableColumnsParams), HttpStatus.OK);
        log.info("Jasper_Report Generated Successfully");
        return response;

    }

    /**
     *This controller Method is used to fetch the list of columns and child Tables for Multiple Tables of a particular schema
     */
    @GetMapping(value = "/{schemaName}")
    @Operation(summary = "To fetch Multiple Entity Properties and child Tables in a particular schema of a Multiple Entity")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the Multiple Entity Properties and Child Tables"),
            @ApiResponse(responseCode = "400", description = "Invalid Entity/Table Name",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Not Found the Multiple Entity Properties and Child Tables",
                    content = @Content) })
    public ResponseEntity<?> getMultipleTableColumnsAndChildTables(@Parameter(description = "SchemaName of database")@RequestParam String schemaName,
                                                                   @Parameter(description = "List of Tables of Schema") @RequestBody List<String> tableNames) throws Exception {

        ResponseEntity<?> response = new ResponseEntity<>(employeeService.getMultipleTableColumnsAndChildTables(schemaName,tableNames),
                HttpStatus.OK);
        log.info("Table Columns and child Tables Data fetched successfully for the given Multiple Tables ");
        return response;

    }

    /**
     * This controller Method is used to fetch the list of schemas in database
     */
    @GetMapping(value = "/")
    @Operation(summary = "To fetch list of schemas in a database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of schemas was fetched"),
            @ApiResponse(responseCode = "400", description = "No schemas found at database",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Invalid Database connection",
                    content = @Content) })
    public ResponseEntity<?> getSchemas() throws Exception {

        ResponseEntity<?> response = new ResponseEntity<>(employeeService.getSchemas(), HttpStatus.OK);
        log.info("Schemas are fetched successfully for the database");
        return response;

    }

    /**
     * This controller Method is used to print the multiple reports in a single page
     */
    @GetMapping(value = "/multiple/{recentStyle}")
    public ResponseEntity<?> generateReport(@RequestParam boolean recentStyle, @RequestBody MultiTableColumnsResult multiTableColumnsResult) throws Exception {

        ResponseEntity<?> response = new ResponseEntity<>(jasperReportService.multipleReports(recentStyle, multiTableColumnsResult), HttpStatus.OK);
        return response;

    }

}
