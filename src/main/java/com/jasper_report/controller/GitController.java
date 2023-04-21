package com.jasper_report.controller;

import com.jasper_report.service.GitService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/git")
@Log4j2
public class GitController {

    @Autowired
    private GitService gitService;

    @GetMapping(value = "/{schemaName}")
    @Operation(summary = "To fetch list of tables in a particular schema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the Tables"),
            @ApiResponse(responseCode = "400", description = "Invalid Schema",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Tables are not found in the mentioned schema",
                    content = @Content) })
    public ResponseEntity<?> getTablesList(@Parameter(description = "schemaName of database from which tables has to fetch") @RequestParam String schemaName) throws Exception {

        ResponseEntity<?> response = new ResponseEntity<>(gitService.getTablesList(schemaName), HttpStatus.OK);
        log.info("All the tables are fetched from schema :"+ schemaName);
        return response;

    }


}
