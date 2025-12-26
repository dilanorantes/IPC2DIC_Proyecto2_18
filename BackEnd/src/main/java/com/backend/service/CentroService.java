package com.backend.service;
import com.backend.model.CentroDistribucion;
import java.util.ArrayList;

//los service que no son Impl
// sirven para poner que metodos utilizará cada clase pero no como las utilizará 

public interface CentroService {
    CentroDistribucion obtenerCentroPorId(String id);
}

