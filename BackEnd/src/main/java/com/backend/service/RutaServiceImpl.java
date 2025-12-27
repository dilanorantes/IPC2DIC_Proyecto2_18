package com.backend.service;

import com.backend.lista.Listas;
import com.backend.model.Paquete;
import com.backend.model.Ruta;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.IllegalFormatCodePointException;

@Service
public class RutaServiceImpl implements RutaService {

    public RutaServiceImpl() {

    }

    //metodo para mandar lista de rutas
    @Override
    public ArrayList<Ruta> obtenerRutas() {
        return Listas.listaRutas;
    }

    @Override
    public Ruta obtenerRutaPorId(String id) {
        for (Ruta ruta : Listas.listaRutas) {
            if (ruta.getIde().equals(id)) {
                return ruta;
            }
        }
        return null;
    }


    public Boolean existeRutaSimilar(Ruta rutaEnvi) {
            Ruta ruta = obtenerRutaPorId(rutaEnvi.getIde());
            //si no encuentra una ruta igual ruta será null entonces sigue
            if (ruta == null) {
                //si la ruta de origen enviada no es igual a su ruta destino sigue
                if (!rutaEnvi.getOrigen().equals(rutaEnvi.getDestino())) {
                    //leo todas las rutas si alguna tiene el mismo origen y el mismo destino regresa falso
                    for (Ruta ruta_actual : Listas.listaRutas) {
                        if (rutaEnvi.getOrigen().equals(ruta_actual.getOrigen()) && rutaEnvi.getDestino().equals(ruta_actual.getDestino())) {
                            return true;
                        }
                    }
                    //sino quiere decir que son totalmente diferentes y regresa un false porque no son similares
                    return false;
                }return  true;
            }return true;
    }

}
