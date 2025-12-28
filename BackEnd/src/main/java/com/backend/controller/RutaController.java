package com.backend.controller;



import com.backend.model.Ruta;
import com.backend.service.RutaService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
//http://localhost:8080/rutas
@RequestMapping("/rutas")
public class RutaController {
    private final RutaService rutaService;
    public RutaController(RutaService rutaService) {
        this.rutaService = rutaService;
    }

    //metodo get http://localhost:8080/rutas para pedir una lista, la toma de la lista service de rutasServices
    // , y a su vez esta está implementada en  rutasServiceImplement
    @GetMapping
    public ArrayList<Ruta> listarRutas() {
        return rutaService.obtenerRutas();
    }


}
