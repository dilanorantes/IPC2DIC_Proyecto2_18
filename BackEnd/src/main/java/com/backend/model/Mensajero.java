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

    public int getCapacidad_envio() {
        return capacidad_envio;
    }
    public String getCentro_asig() {
        return centro_asig;
    }
    public String getEstado() {
        return estado;
    }

    //setters
    public void setEstado(String estado) {
        this.estado = estado;
    }
    public void setIde(String ide) {
        this.ide = ide;
    }
    public void setNombre(String nombre) {

    }
    public void setCapacidad_envio(int capacidad_envio) {
        this.capacidad_envio = capacidad_envio;

    }
    public void setCentro_asig(String centro_asig) {
        this.centro_asig = centro_asig;
    }


}
