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

        //si se crea una ruta con una distancia en string le agrego distancia negativa para saber el error
        try {
            this.distancia = Integer.parseInt(distancia);
        } catch (NumberFormatException e) {
            this.distancia = -1;
        }
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


    public void setIde(String ide) {
        this.ide = ide;
    }
    public void setDestino(String destino) {
        this.destino = destino;
    }
    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public void setDistancia(int distancia) {
        this.distancia = distancia;
    }


}
