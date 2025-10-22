package com.apiReport.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class HealthController {

	/**
	 * Endpoint para verificar que la API está funcionando
	 * 
	 * @return Estado de la API
	 */
	@GetMapping("/health")
	public ResponseEntity<Map<String, Object>> health() {
		Map<String, Object> response = new HashMap<>();
		response.put("status", "UP");
		response.put("message", "API funcionando correctamente");
		response.put("timestamp", LocalDateTime.now());
		response.put("features", new String[] { "QR Code Generation" });
		return ResponseEntity.ok(response);
	}

	/**
	 * Endpoint raíz de bienvenida
	 * 
	 * @return Mensaje de bienvenida
	 */
	@GetMapping("/")
	public ResponseEntity<Map<String, String>> welcome() {
		Map<String, String> response = new HashMap<>();
		response.put("api", "ApiReport");
		response.put("version", "1.0.0");
		response.put("description", "API para generación de códigos QR y reportes");
		response.put("documentation", "/api/health para ver el estado");
		return ResponseEntity.ok(response);
	}
}
