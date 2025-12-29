package com.backend.service;

import com.backend.model.Solicitud;

import java.util.PriorityQueue;

public interface SolicitudService {
    PriorityQueue<Solicitud> obtenerSolicitudes();


    Solicitud  procesar1Solicitud();
    boolean eliminarSolicitud(String id);

    Solicitud obtenerSolicitudPorId(String id);

    Boolean SepuedeProcesarSoli(Solicitud solicitud);
}
