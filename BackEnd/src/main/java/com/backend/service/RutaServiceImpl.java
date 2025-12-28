package com.backend.service;

import com.backend.lista.Listas;
import com.backend.model.Ruta;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

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

    @Override
    public Ruta crearRutaNueva(Ruta rutaNueva){
        //si existe la ruta o tenia parametros malos no la agrega y retorna null
        if (existeRutaSimilar(rutaNueva)) {
            return null;
        }
        Listas.listaRutas.add(rutaNueva);
        return rutaNueva;
    }

    @Override
    public Ruta editarRuta(String id, Ruta rutaRecibida){
        //obtengo la ruta a editar y la guardo

        Ruta rutaAEditar = obtenerRutaPorId(id);

        //si si existe entonces , edito sus atributos traidos por rutarecibida
        if (rutaAEditar != null) {

            if (existeRutaOrigenDestino(rutaRecibida)) {
                return null;
            }
            rutaAEditar.setOrigen(rutaRecibida.getOrigen());
            rutaAEditar.setDestino(rutaRecibida.getDestino());
            rutaAEditar.setDistancia(rutaRecibida.getDistancia());
            return rutaAEditar;
        }
        return null;
    }

    @Override
    public Boolean existeRutaSimilar(Ruta rutaEnvi) {
            Ruta ruta = obtenerRutaPorId(rutaEnvi.getIde());
            //si no encuentra una ruta igual ruta será null entonces sigue
            if (ruta == null) {
                //si la ruta de origen enviada no es igual a su ruta destino sigue
                if (!rutaEnvi.getOrigen().equals(rutaEnvi.getDestino())) {
                    //leo todas las rutas si alguna tiene el mismo origen y el mismo destino regresa true
                    for (Ruta ruta_actual : Listas.listaRutas) {
                        if (rutaEnvi.getOrigen().equals(ruta_actual.getOrigen()) && rutaEnvi.getDestino().equals(ruta_actual.getDestino())) {
                            System.out.println("ruta ya tiene origen destino que otro " + rutaEnvi.getOrigen() + " " + rutaEnvi.getDestino()+"\n");
                            return true;
                        }
                    }
                    //sino quiere decir que son totalmente diferentes y regresa un false porque no son similares
                    return false;
                }
                System.out.println("ruta ya tiene origen destino " + rutaEnvi.getOrigen() + " " + rutaEnvi.getDestino() +"\n");
                return  true;
            }System.out.println(ruta.getIde()+" ruta ya  existe\n");
            return true;
    }

    @Override
    public Boolean existeRutaOrigenDestino(Ruta rutaEnvi){
        if (rutaEnvi.getOrigen().equals(rutaEnvi.getDestino())){
            //si su mismo origen destino es igual tambien regreso tru
            return true;
            //para indicar que es lo mismo
        }
        for (Ruta ruta_actual : Listas.listaRutas) {
            //si no es la misma ruta que estoy viendo puedo seguir y
            if (ruta_actual.getIde().equals(rutaEnvi.getIde()) ) {
                System.out.println("encontré la misma ruta, no comparo");
            }
            else {
                if (rutaEnvi.getOrigen().equals(ruta_actual.getOrigen()) && rutaEnvi.getDestino().equals(ruta_actual.getDestino())){
                    System.out.println("ruta ya tiene origen destino que otro " + rutaEnvi.getOrigen() + " " + rutaEnvi.getDestino()+"\n");
                return true;
                }
            }
        }
        //si recorri toda la lista rutas y no encontró ninguna igual a sus origen destino puedo decir que no hay , existerutaorigenDest  ES FALSO
        return false;
    }

}
