package com.apiReport.demo.exception;

public class ReportException extends RuntimeException {

    public ReportException(String message) {
        super(message);
    }

    public ReportException(String message, Throwable cause) {
        super(message, cause);
    }

    public ReportException(Throwable cause) {
        super(cause);
    }
}
