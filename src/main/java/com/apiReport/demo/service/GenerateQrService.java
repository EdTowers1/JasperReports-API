package com.apiReport.demo.service;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class GenerateQrService {

	/**
	 * Genera un código QR a partir de un texto o valor dado con tamaño
	 * personalizado
	 */
	public byte[] generateQrCode(String content, int width, int height) throws WriterException, IOException {
		QRCodeWriter qrCodeWriter = new QRCodeWriter();
		BitMatrix bitMatrix = qrCodeWriter.encode(content, BarcodeFormat.QR_CODE, width, height);

		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		MatrixToImageWriter.writeToStream(bitMatrix, "PNG", outputStream);

		return outputStream.toByteArray();
	}

	/**
	 * Genera un código QR con tamaño predeterminado 300x300
	 */
	public byte[] generateQrCode(String content) throws WriterException, IOException {
		return generateQrCode(content, 300, 300);
	}

	/**
	 * Genera un QR y lo guarda en la carpeta indicada (o en 'qrcodes' si se pasa
	 * null).
	 * El nombre por defecto es 'qr_{sanitized_content}.png' y se evita sobrescribir
	 * archivos existentes.
	 */
	public String saveQrCodeToFolder(String content, String folderPath, String filename)
			throws WriterException, IOException {
		byte[] image = generateQrCode(content);

		Path dir = Paths.get(folderPath == null || folderPath.isBlank() ? "qrcodes" : folderPath);
		if (!Files.exists(dir)) {
			Files.createDirectories(dir);
		}

		String finalName;
		if (filename == null || filename.isBlank()) {
			String base = (content == null) ? "qr" : content;
			String sanitized = base.toLowerCase().replaceAll("[^a-z0-9\\-_]", "_");
			if (sanitized.length() > 50)
				sanitized = sanitized.substring(0, 50);
			String candidate = "qr_" + sanitized + ".png";
			Path file = dir.resolve(candidate);
			if (Files.exists(file)) {
				candidate = "qr_" + sanitized + "_" + System.currentTimeMillis() + ".png";
				file = dir.resolve(candidate);
			}
			finalName = file.getFileName().toString();
		} else {
			finalName = filename;
			if (!finalName.toLowerCase().endsWith(".png"))
				finalName = finalName + ".png";
		}

		Path file = dir.resolve(finalName);
		Files.write(file, image);
		return file.toAbsolutePath().toString();
	}
}
