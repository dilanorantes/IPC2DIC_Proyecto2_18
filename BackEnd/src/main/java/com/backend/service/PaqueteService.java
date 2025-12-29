package com.backend.service;
import com.backend.model.Paquete;

import java.util.ArrayList;

public interface PaqueteService {
    ArrayList<Paquete> obtenerPaquetes();

    //metodo para encontrar un paquete por id
    Paquete obtenerPaquetePorId(String id);
}
