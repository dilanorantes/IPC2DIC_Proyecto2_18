package com.backend.model;

public class Ruta {
    private String ide;
    String origen;
    String destino;
    int distancia;

    public Ruta(String ide, String origen, String destino, String distancia) {
        this.ide = ide;
        this.origen = origen;
        this.destino = destino;
        this.distancia = Integer.parseInt(distancia);
    }

    public String getIde() {
        return ide;
    }

    public String getOrigen() {
        return origen;
    }
    public String getDestino() {
        return destino;
    }

    public int getDistancia() {
        return distancia;
    }
}
