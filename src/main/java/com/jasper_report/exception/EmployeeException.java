package com.jasper_report.exception;


public class EmployeeException extends RuntimeException{

    static final long serialVersionUID = -7034897190745766939L;

    public EmployeeException() {
        super();
    }

    public EmployeeException(String message) {
        super(message);
    }


    public EmployeeException(String message, Throwable cause) {
        super(message, cause);
    }

    public EmployeeException(Throwable cause) {
        super(cause);
    }

    protected EmployeeException(String message, Throwable cause,
                                boolean enableSuppression,
                                boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}

