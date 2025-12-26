package com.backend.service;

//importo mi modelo de clase centrodistribucion
import com.backend.model.CentroDistribucion;

import org.springframework.stereotype.Service;

import java.util.ArrayList;


@Service

public class CentroServiceImpl implements  CentroService {
    //creo lista_centros
    private final ArrayList<CentroDistribucion> lista_centros = new ArrayList<>();


    //este es el constructor de centroservice
    public CentroServiceImpl() {
        //aqui se crean todos los centros y se agregan a la lista
        lista_centros.add(new CentroDistribucion("CD001", "centro1","ciudad1", "33"));
        lista_centros.add(new CentroDistribucion("CD002", "centro2","ciudad2", "40"));

    }
    //metodo para devolver la lista de los centros
    @Override
    public ArrayList<CentroDistribucion> obtenerCentros() {
        return lista_centros;
    }

    @Override

    //metodo para encontrar un centro por su id
    public CentroDistribucion obtenerCentroPorId(String id) {
        for (CentroDistribucion centro : lista_centros) {
            if (centro.getIdcentro().equals(id)) {
                return centro;
            }
        }
        return null;
    }


    //aqui hay que llamar la lista de paquetes y ver cuales tienen centro actual == id centro
    //crear una lista y regresarla
    @Override
    public CentroDistribucion listaPaquetesDelCentro(String id_centro) {
        for (CentroDistribucion centro : lista_centros) {
            if (centro.getIdcentro().equals(id_centro)) {
                return centro;
            }
        }
        return null;
    }


    //aqui hay que llamar la lista de mensajeros y ver cuales tienen centro_asig == id centro
    //crear una lista y regresarla
    @Override
    public CentroDistribucion listaMensajerosDelCentro (String id_centro) {
        for (CentroDistribucion centro : lista_centros) {
            if (centro.getIdcentro().equals(id_centro)) {
                return centro;
            }
        }
        return null;
    }


}