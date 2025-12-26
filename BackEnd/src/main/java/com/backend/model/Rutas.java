package com.backend.model;

public class Rutas {
    private String ide;
    String origen;
    String destino;
    int distancia;

    public Rutas(String ide, String origen, String destino, String distancia) {
        this.ide = ide;
        this.origen = origen;
        this.destino = destino;
        this.distancia = Integer.parseInt(distancia);
    }

    public String getIde() {
        return ide;
    }

}
