package com.backend.controller;

import com.backend.lista.ColaPriori;
import com.backend.model.Solicitud;
import com.backend.service.SolicitudService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.PriorityQueue;

@RestController
//http://localhost:8080/solicitudes
@RequestMapping("/solicitudes")
public class SolicitudController {
    private final SolicitudService solicitudService;
    public SolicitudController(SolicitudService solicitudService) {
        this.solicitudService = solicitudService;
    }
    @GetMapping
    public PriorityQueue<Solicitud> getSolicitudes(){
        return solicitudService.obtenerSolicitudes();
    }

    @PostMapping ("/procesar")
    public ResponseEntity<?> procesar1Solicitud(){
        Solicitud solicitud = ColaPriori.colaPriori.peek();
        if (ColaPriori.colaPriori.isEmpty()) {
            return ResponseEntity.badRequest().body("Ya no hay mas solicitudes por procesar");
        }
        if (solicitudService.SepuedeProcesarSoli(solicitud)){
            solicitudService.procesar1Solicitud();

            return ResponseEntity.ok(solicitud);
        }
        else {
            return ResponseEntity.badRequest().body("La solicitud no se pudo procesar");
        }
    }

    //Con un DELETE Y http://localhost:8080/solicitudes/{ID}
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarSolicitudporId(@PathVariable String id) {
        if (solicitudService.obtenerSolicitudPorId(id) == null) {
            return ResponseEntity.badRequest().body("No se eliminó porque no existe esa solicitud");
        }
        return ResponseEntity.ok(solicitudService.eliminarSolicitud(id));
    }



}