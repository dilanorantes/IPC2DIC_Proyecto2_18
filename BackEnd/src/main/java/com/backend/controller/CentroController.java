package com.backend.controller;
import com.backend.model.CentroDistribucion;
import com.backend.model.Mensajero;
import com.backend.model.Paquete;
import com.backend.service.CentroService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;


@RestController
@RequestMapping("/centros")
public class CentroController {
    private final CentroService centroService;

    public CentroController(CentroService centroService) {
        this.centroService = centroService;
    }

    //metodo get http://localhost:8080/centros para pedir una lista, la toma de la lista service del centro
    // , y a su vez esta está implementada en CentroservicesImpl
    @GetMapping
    public ArrayList<CentroDistribucion> listarCentros() {
        return centroService.obtenerCentros();
    }

    //path variable dependiendo de la id
    @GetMapping("/{id}")
    public ResponseEntity<?>  obtenerCentro(@PathVariable String id) {
        CentroDistribucion centroEncontrado = centroService.obtenerCentroPorId(id);
        if (centroEncontrado == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(centroEncontrado);
    }

    //aqui va el get de la lista de paquetes

    //http://localhost:8080/centros/id/paquetes
    @GetMapping("/{id}/paquetes")
    public ArrayList<Paquete> obtenerPaquetesDelCentro(@PathVariable String id) {
        return centroService.listaPaquetesDelCentro(id);
    }

    @GetMapping("/{id}/mensajeros")
    public ArrayList<Mensajero> obteMensajerosDelCentro(@PathVariable String id) {
        return centroService.listarMensajerosCentro(id);
    }



}
