package com.agencia.pf.controllers;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

@CrossOrigin
@RestController
@RequestMapping("/pdftoexcel")
public class PdfToExcelController {

    @PostMapping("/upload")
    public ResponseEntity<byte[]> uploadPdf(@RequestParam("file") MultipartFile file) throws IOException {
        // Verificar que el archivo sea un PDF
        if (!file.getContentType().equals("application/pdf")) {
            return ResponseEntity.status(HttpStatus.UNSUPPORTED_MEDIA_TYPE).build();
        }

        // Procesar el PDF y convertirlo a Excel
        byte[] excelContent = processPdfToExcel(file);

        // Configurar la respuesta
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=recibo.xlsx");
        return new ResponseEntity<>(excelContent, headers, HttpStatus.OK);
    }

    private byte[] processPdfToExcel(MultipartFile file) throws IOException {
        // Aquí va la lógica para extraer datos del PDF y crear el Excel
        return new byte[0];
    }
}