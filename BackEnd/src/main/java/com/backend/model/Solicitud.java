package com.backend.model;

public class Solicitud {
    private String ide;
    String tipo;
    String paquete_a_enviar;
    int prioridad;
    String estado_solicitud;

    public Solicitud(String ide, String tipo, String paquete_a_enviar, String prioridad) {
        this.ide = ide;
        this.tipo = tipo;
        this.paquete_a_enviar = paquete_a_enviar;
        this.prioridad = Integer.parseInt(prioridad);
        this.estado_solicitud = "PENDIENTE";
    }

    public String getIde() {
        return ide;
    }
    public void setIde(String ide) {
        this.ide = ide;
    }

    public String getTipo() {
        return tipo;
    }
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getPaquete_a_enviar() {
        return paquete_a_enviar;
    }
    public void setPaquete_a_enviar(String paquete_a_enviar) {
        this.paquete_a_enviar = paquete_a_enviar;
    }
    public int getPrioridad() {
        return prioridad;
    }
    public void setPrioridad(int prioridad) {
        this.prioridad = prioridad;
    }
}
