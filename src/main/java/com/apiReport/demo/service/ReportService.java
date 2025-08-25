package com.apiReport.demo.service;

import com.apiReport.demo.exception.ReportException;
import com.apiReport.demo.model.Cliente;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ReportService {

    public byte[] exportarReporteCliente(Cliente cliente, String template) {
        return exportarReporte(List.of(cliente), template);
    }

    public byte[] exportarReporteClientes(List<Cliente> clientes, String template) {
        return exportarReporte(clientes, template);
    }

    private byte[] exportarReporte(List<Cliente> clientes, String template) {
        ClassPathResource resource = new ClassPathResource("reports/" + template);
        try (InputStream inputStream = resource.getInputStream()) {
            JasperReport jasperReport = JasperCompileManager.compileReport(inputStream);
            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(clientes);
            Map<String, Object> parameters = new HashMap<>();
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
            return JasperExportManager.exportReportToPdf(jasperPrint);
        } catch (Exception e) {
            throw new ReportException("Error al exportar el reporte Jasper: " + template, e);
        }
    }
}
