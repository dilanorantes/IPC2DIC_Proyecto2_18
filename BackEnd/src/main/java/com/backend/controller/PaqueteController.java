package com.backend.controller;

import com.backend.model.Paquete;
import com.backend.service.PaqueteService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
//http://localhost:8080/paquetes
@RequestMapping("/paquetes")
public class PaqueteController {
    private final PaqueteService paqueteService;

    public PaqueteController(PaqueteService paqueteService) {
        this.paqueteService = paqueteService;
    }

    @GetMapping
    public ArrayList<Paquete> listarPaquetes() {
        return paqueteService.obtenerPaquetes();
    }


}
