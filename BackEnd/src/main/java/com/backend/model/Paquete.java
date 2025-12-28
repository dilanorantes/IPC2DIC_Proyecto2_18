package com.backend.model;

public class Paquete {
    private String ide;
    String cliente;
    int peso;
    String destino;
    String estado;
    String centro_actual;
    //constructor que se usan cuando si viene con parametro estado en xml
    public Paquete(String ide, String cliente, String peso, String destino,String estado, String centro_actual) {
        this.ide = ide;
        this.cliente = cliente;
        this.peso = Integer.parseInt(peso);
        this.destino = destino;
        this.centro_actual = centro_actual;
        this.estado = estado;
    }
    //constructor para crear un nuevo paquete, cuando no se le envia estado
    public Paquete(String ide, String cliente, String peso, String destino, String centro_actual) {
        this.ide = ide;
        this.cliente = cliente;
        this.peso = Integer.parseInt(peso);
        this.destino = destino;
        this.centro_actual = centro_actual;
        this.estado = "PENDIENTE";
    }

    //getters para paquetes, sirve para que la lista al enviarse manden la info
    public String getIde() {
        return ide;
    }
    public String getCliente() {
        return cliente;
    }
    public int getPeso() {
        return peso;
    }
    public String getDestino() {
        return destino;
    }
    public String getEstado() {
        return estado;
    }
    public String getCentro_actual() {
        return centro_actual;
    }

}
