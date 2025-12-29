package com.backend.controller;



import com.backend.model.Ruta;
import com.backend.service.RutaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    //Con un POST Y  http://localhost:8080/rutas creamos una nuevaruta por un json que trae
    @PostMapping
    public ResponseEntity<?> guardarNuevaRuta(@RequestBody Ruta ruta) {
        if (ruta.getDistancia() == -1 || ruta.getIde().isEmpty() ||  ruta.getOrigen().isEmpty() || ruta.getDestino().isEmpty()) {
            return ResponseEntity.badRequest().body("la distancia esta como string o campos vacios");
        } else if (ruta.getDistancia() <= 0) {
            return ResponseEntity.badRequest().body("La distancia no puede ser negativa");
        } else if (rutaService.crearRutaNueva(ruta) == null) {
            return ResponseEntity.badRequest().body("Ya existe ruta similar o has agregado origen destino similar");
        }
        Ruta rutaNueva = rutaService.crearRutaNueva(ruta);

        return ResponseEntity.ok(rutaNueva);
    }


    //Con un PUT Y  http://localhost:8080/rutas/{ID} editamos  una ruta por un json que trae Y un id
    @PutMapping("{id}")
    public ResponseEntity<?> editarRuta(@PathVariable String id, @RequestBody Ruta ruta) {
        if (rutaService.obtenerRutaPorId(id) == null) {
            return ResponseEntity.badRequest().body("no existe esa ruta por id");
        } else if (ruta.getDistancia() == -1 || ruta.getIde().isEmpty() ||  ruta.getOrigen().isEmpty() || ruta.getDestino().isEmpty()) {
            return ResponseEntity.badRequest().body("la distancia esta como string o campos vacios");
        } else if (ruta.getDistancia() <= 0) {
            return ResponseEntity.badRequest().body("La distancia no puede ser negativa");


        } else if (rutaService.existeRutaOrigenDestino(ruta)) {
            return ResponseEntity.badRequest().body("Ya existe ruta similar o has agregado origen destino similar");
        }
        // si nohay errores lo actualiza
        Ruta rutaNueva = rutaService.editarRuta(id, ruta);

        return ResponseEntity.ok(rutaNueva);
    }

    //Con un DELETE Y  http://localhost:8080/rutas/{ID}
    @DeleteMapping("{id}")
    public ResponseEntity<?> eliminarRuta(@PathVariable String id) {
        if (rutaService.obtenerRutaPorId(id) == null) {
            return ResponseEntity.badRequest().body("no se eliminó por que no existe esa ruta ");
        }
        return  ResponseEntity.ok(rutaService.eliminarRuta(id));
    }


}
