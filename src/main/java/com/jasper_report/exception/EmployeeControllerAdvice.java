package com.jasper_report.exception;

import org.postgresql.util.PSQLException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


/**
 * This class is Global_Exception_Handler
 * This class handles each and every exception which has thrown any exception from any corner of the Program
 */
@ControllerAdvice
public class EmployeeControllerAdvice {

    @ExceptionHandler(EmployeeException.class)
    public ResponseEntity<Object> handleEmployeeException(EmployeeException exp) {
        return new ResponseEntity<>(exp.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);

    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    private ResponseEntity<Object> handleMissingServletRequestParameterException(MissingServletRequestParameterException msg) {
        return new ResponseEntity<>("Pls check the passing parameters/body ",HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NullPointerException.class)
    private ResponseEntity<Object> handleNullPointerException(NullPointerException msg) {
        return new ResponseEntity<>("Pls check, you ( passed a null value for )/missed : parameters/body",HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(PSQLException.class)
    private ResponseEntity<Object> handlePSQLException(PSQLException msg) {
        return new ResponseEntity<>("Pls check, you passed an empty/incorrect value for parameters/body",HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    private ResponseEntity<Object> handleHttpMessageNotReadableException(HttpMessageNotReadableException msg) {
        return new ResponseEntity<>("Pls check the format of passing Json body",HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
