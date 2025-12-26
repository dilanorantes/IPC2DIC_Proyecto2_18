package com.backend.service;

//importo mi modelo de clase centrodistribucion
import com.backend.model.CentroDistribucion;

import org.springframework.stereotype.Service;

import java.util.ArrayList;


@Service

public class CentroServiceImpl implements  CentroService {
    //creo lista_centros
    private final ArrayList<CentroDistribucion> lista_centros = new ArrayList<>();


    public CentroServiceImpl() {
        //aqui se crean todos los centros y se agregan a la lista
        lista_centros.add(new CentroDistribucion("CD001", "centro1","ciudad1", "33"));
    }

    @Override

    //metodo para encontrar un objeto por su id
    public CentroDistribucion obtenerCentroPorId(String id) {
        for (CentroDistribucion centro : lista_centros) {
            if (centro.getIdcentro().equals(id)) {
                return centro;
            }
        }
        return null;
    }
}