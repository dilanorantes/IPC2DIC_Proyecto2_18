package com.backend.model;

public class Solicitudes {
    private String ide;
    String tipo;
    String paquete_a_enviar;
    int prioridad;
    String estado_solicitud;

    public Solicitudes(String ide, String tipo, String paquete_a_enviar, String prioridad) {
        this.ide = ide;
        this.tipo = tipo;
        this.paquete_a_enviar = paquete_a_enviar;
        this.prioridad = Integer.parseInt(prioridad);
        this.estado_solicitud = "PENDIENTE";
    }

    public String getIde() {
        return ide;
    }

}
