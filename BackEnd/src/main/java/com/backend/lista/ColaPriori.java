package com.backend.lista;

import com.backend.model.Solicitud;

import java.util.PriorityQueue;

public class ColaPriori {

    public static PriorityQueue<Solicitud> colaPriori = new PriorityQueue<>(
            (soli1, soli2) -> {
                //toma ambas solicitudes y las compara, si la soli enviada (soli1) es mayor priori entonces
                //el resultado es negativo lo que le indica a la priorityqueue que la agregega antes que la soli comparada
                return soli2.getPrioridad() - soli1.getPrioridad();
            }
    );
}