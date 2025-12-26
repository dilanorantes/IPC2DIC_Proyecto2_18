package com.backend.model;

public class CentroDistribucion {
    private String idcentro;
    private String nombre;
    private String ciudad;
    private int capacidad_total;
    int capacidad_libre;

    public CentroDistribucion(String idcentro, String nombre, String ciudad, String capacidad_total) {
        this.idcentro = idcentro;
        this.nombre = nombre;
        this.ciudad = ciudad;
        this.capacidad_total = Integer.parseInt(capacidad_total);
        this.capacidad_libre = this.capacidad_total;
    }

    //getters para los atributos privados
    public String getIdcentro() {
        return idcentro;
    }

    public String getNombre() {
        return nombre;
    }

    public String getCiudad() {
        return ciudad;
    }

    public int getCapacidad_total() {
        return capacidad_total;
    }
}
