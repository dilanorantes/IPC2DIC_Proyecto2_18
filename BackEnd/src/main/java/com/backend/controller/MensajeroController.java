package com.backend.controller;

import com.backend.model.Mensajero;
import com.backend.service.MensajeroService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping("/mensajeros")
public class MensajeroController {
    private MensajeroService mensajeroService;

    public MensajeroController(MensajeroService mensajeroService) {
        this.mensajeroService = mensajeroService;
    }

    //metodo para la lista de todos los mensajeros se llaman con get //http://localhost:8080/mensajeros
    @GetMapping
        public ArrayList<Mensajero> listarMensajeros(){
        return mensajeroService.obtenerMensajeros();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerMensajeroPorId(@PathVariable String id) {
        Mensajero mensajeroEncontrado = mensajeroService.obtenerMensajeroPorId(id);
        if (mensajeroEncontrado != null) {
            return ResponseEntity.ok().body(mensajeroEncontrado);
        }
        return ResponseEntity.notFound().build();
    }

}
