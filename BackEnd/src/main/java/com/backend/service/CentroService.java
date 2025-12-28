package com.backend.service;
import com.backend.model.CentroDistribucion;
import com.backend.model.Mensajero;
import com.backend.model.Paquete;

import java.util.ArrayList;

//los service que no son Impl
// sirven para poner que metodos utilizará cada clase pero no como las utilizará 

public interface CentroService {
    ArrayList<CentroDistribucion> obtenerCentros();

    //metodos para encontrar la información especifica de un centro por su id
    CentroDistribucion obtenerCentroPorId(String id);


    ArrayList<Paquete> listaPaquetesDelCentro(String id);

    ArrayList<Mensajero> listarMensajerosCentro(String id_centro);
}

