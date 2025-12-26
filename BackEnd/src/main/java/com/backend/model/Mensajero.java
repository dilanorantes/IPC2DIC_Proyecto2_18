package com.backend.model;

public class Mensajero {
    private String ide;
    private String nombre;
    int capacidad_envio;
    String centro_asig;
    String estado;

    public Mensajero(String ide, String nombre, String capacidad_envio, String centro_asig) {
        this.ide = ide;
        this.nombre = nombre;
        this.capacidad_envio = Integer.parseInt(capacidad_envio);
        this.centro_asig = centro_asig;
        this.estado = "DISPONIBLE";
    }

    public String getIde() {
        return ide;
    }

    public String getNombre() {
        return nombre;
    }


}
