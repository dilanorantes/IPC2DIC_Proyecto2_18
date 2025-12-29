package com.backend.service;

import com.backend.lista.Listas;
import com.backend.model.Mensajero;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class MensajeroServiceImpl implements MensajeroService {
    public MensajeroServiceImpl() {}


    @Override
    public ArrayList<Mensajero> obtenerMensajeros() {
        return Listas.listaMensajeros;
    }

    @Override
    public Mensajero obtenerMensajeroPorId(String id) {
        for (Mensajero mensajero : Listas.listaMensajeros) {
            if (mensajero.getIde().equals(id)) {
                return mensajero;
            }
        }
        return null;
    }
}
