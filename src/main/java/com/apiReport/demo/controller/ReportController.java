package com.apiReport.demo.controller;

import com.apiReport.demo.model.Cliente;
import com.apiReport.demo.service.ClienteService;
import com.apiReport.demo.service.ReportService;
import com.apiReport.demo.exception.ReportNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/reportes")
public class ReportController {

    private final ClienteService clienteService;
    private final ReportService reportService;

    public ReportController(ClienteService clienteService, ReportService reportService) {
        this.clienteService = clienteService;
        this.reportService = reportService;
    }

    // Endpoint para reporte de un solo cliente
    @GetMapping("/cliente/{id}")
    public ResponseEntity<byte[]> reporteCliente(@PathVariable Long id) {
        Cliente cliente = clienteService.obtenerPorId(id)
                .orElseThrow(() -> ReportNotFoundException.forClienteId(id));
        byte[] pdf = reportService.exportarReporteCliente(cliente, "cliente.jrxml");    
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=cliente_" + id + ".pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .body(pdf);
    }

    // Endpoint para reporte general de clientes
    @GetMapping("/clientes")
    public ResponseEntity<byte[]> reporteClientes() {
        List<Cliente> clientes = clienteService.obtenerTodos();
        byte[] pdf = reportService.exportarReporteClientes(clientes, "clientes.jrxml");
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=clientes.pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .body(pdf);
    }

    // Endpoint para verificar que la API está funcionando
    @GetMapping("/status")
    public ResponseEntity<String> status() {
        return ResponseEntity.ok("La API de reportes está funcionando correctamente.");
    }
}
