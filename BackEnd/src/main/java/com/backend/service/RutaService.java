package com.backend.service;

import com.backend.model.Ruta;

import java.util.ArrayList;

public interface RutaService {
    ArrayList<Ruta> obtenerRutas();
    //metodos para encontrar la la ruta por su id
    Ruta obtenerRutaPorId(String id);
}
