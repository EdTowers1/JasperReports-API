package com.apiReport.demo.exception;

public class ReportNotFoundException extends RuntimeException {

    public ReportNotFoundException(String message) {
        super(message);
    }

    public ReportNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public static ReportNotFoundException forReportName(String reportName) {
        return new ReportNotFoundException("No se encontró el reporte: " + reportName);
    }

    public static ReportNotFoundException forClienteId(Long id) {
        return new ReportNotFoundException("No se encontró el cliente con id: " + id);
    }
}
