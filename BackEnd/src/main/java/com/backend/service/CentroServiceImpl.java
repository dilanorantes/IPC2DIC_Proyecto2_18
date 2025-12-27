package com.backend.service;


import com.backend.lista.Listas;
//importo mi modelo de clase centrodistribucion
import com.backend.model.CentroDistribucion;

import com.backend.model.Paquete;
import org.springframework.stereotype.Service;
import com.backend.lista.Listas;
import java.util.ArrayList;
// import com.backend.service.PaqueteService;

@Service

public class CentroServiceImpl implements  CentroService {
    //creo lista_centros
    //private final ArrayList<CentroDistribucion> lista_centros = new ArrayList<>();
    //PaqueteService paqueteService;

    //este es el constructor de centroservice
    public CentroServiceImpl() {
        //this.paqueteService = paqueteService;


        //aqui se crean todos los centros y se agregan a la lista
        Listas.listaCentros.add(new CentroDistribucion("CD001", "centro1","ciudad1", "33"));
        Listas.listaCentros.add(new CentroDistribucion("CD002", "centro2","ciudad2", "40"));

    }
    //metodo para devolver la lista de los centros
    @Override
    public ArrayList<CentroDistribucion> obtenerCentros() {
        return Listas.listaCentros;
    }

    @Override

    //metodo para encontrar un centro por su id
    public CentroDistribucion obtenerCentroPorId(String id) {
        for (CentroDistribucion centro : Listas.listaCentros) {
            if (centro.getIdcentro().equals(id)) {
                return centro;
            }
        }
        return null;
    }


    //aqui hay que llamar la lista de paquetes y ver cuales tienen centro actual == id centro
    //crear una lista y regresarla
    @Override
    public ArrayList<Paquete> listaPaquetesDelCentro(String id_centro) {
        //ArrayList<Paquete> listaPaquetes = paqueteService.obtenerPaquetes();

        //lista temporal de paquetes para enviar se reinicia cada vez
        ArrayList<Paquete> lista_paquetesEnCentro = new ArrayList<>();

        for (Paquete paquete : Listas.listaPaquetes) {
            if (paquete.getCentro_actual().equals(id_centro)) {
                lista_paquetesEnCentro.add(paquete);
            }
        }

        return lista_paquetesEnCentro;
    }


    //aqui hay que llamar la lista de mensajeros y ver cuales tienen centro_asig == id centro
    //crear una lista y regresarla

    @Override
    public CentroDistribucion listaMensajerosDelCentro(String id) {
        return null;
    }
}






