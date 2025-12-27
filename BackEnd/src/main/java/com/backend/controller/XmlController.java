package com.backend.controller;
import  com.backend.lista.lectorXml;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

@RestController
@RequestMapping("/api")
public class XmlController {
    //http://localhost:8080/api/subirxml
    @PostMapping("/subirxml")
    public ResponseEntity<String> uploadXML(@RequestParam("file") MultipartFile file) {
        try {

            InputStream inputStream = file.getInputStream();

            //creamos una instancia del lector y usamos su metodo de lectorxml
            lectorXml lector = new lectorXml();
            lector.leerXml(inputStream);

            return ResponseEntity.ok("Archivo XML procesado correctamente");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al procesar el archivo XML");
        }
    }
}