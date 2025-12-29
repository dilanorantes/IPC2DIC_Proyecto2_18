package com.backend.service;

import com.backend.model.Ruta;

import java.util.ArrayList;

public interface RutaService {
    ArrayList<Ruta> obtenerRutas();
    //metodos para encontrar la la ruta por su id
    Ruta obtenerRutaPorId(String id);


    Ruta crearRutaNueva(Ruta rutaNueva);

    Ruta editarRuta(String id, Ruta rutaRecibida);

    Boolean existeRutaSimilar(Ruta rutaEnvi);


    Boolean existeRutaOrigenDestino(Ruta rutaEnvi);

    Ruta EncontrarPorSuRuta(String origen, String Destino);
}
