package com.backend.model;

public class Paquetes {
    private String ide;
    String cliente;
    int peso;
    String destino;
    String estado;
    String centro_actual;

    public Paquetes(String ide, String cliente, String peso, String destino, String centro_actual) {
        this.ide = ide;
        this.cliente = cliente;
        this.peso = Integer.parseInt(peso);
        this.destino = destino;
        this.centro_actual = centro_actual;
        this.estado = "PENDIENTE";
    }

    public String getIde() {
        return ide;
    }

}
