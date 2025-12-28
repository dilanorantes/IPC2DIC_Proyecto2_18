package com.backend.lista;

import com.backend.model.CentroDistribucion;
import com.backend.model.Paquete;
import com.backend.model.Ruta;
import com.backend.model.Mensajero;
import com.backend.model.Solicitud;

import java.util.ArrayList;

public class Listas {
    public static ArrayList<CentroDistribucion> listaCentros = new ArrayList<>();
    public static ArrayList<Paquete> listaPaquetes = new ArrayList<>();
    public static ArrayList<Ruta> listaRutas = new ArrayList<>();
    public static ArrayList<Mensajero> listaMensajeros = new ArrayList<>();
    //tendria que usar otra lista para solicitudes
    public static ArrayList<Solicitud> listaSolicitudes = new ArrayList<>();
}