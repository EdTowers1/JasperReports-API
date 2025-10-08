package com.apiReport.demo.controller;

import com.apiReport.demo.service.GenerateQrService;
import com.google.zxing.WriterException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("/api/qr")
public class GenerateQrController {

	@Autowired
	private GenerateQrService generateQrService;

	/**
	 * Endpoint para generar un código QR y guardarlo automáticamente en la carpeta
	 * qrcodes
	 * 
	 * @param content El contenido que se codificará en el QR
	 * @return JSON con la ruta donde se guardó el archivo
	 */
	@GetMapping("/generate")
	public ResponseEntity<Object> generateQrCode(@RequestParam String content) {
		try {
			String path = generateQrService.saveQrCodeToFolder(content, "qrcodes", null);
			return ResponseEntity.ok(Map.of("path", path, "message", "QR generado exitosamente"));
		} catch (WriterException | IOException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(Map.of("error", e.getMessage()));
		}
	}

}
