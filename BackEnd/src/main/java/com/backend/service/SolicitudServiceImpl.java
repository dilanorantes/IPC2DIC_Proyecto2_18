package com.backend.service;

import com.backend.lista.ColaPriori;
import com.backend.model.CentroDistribucion;
import com.backend.model.Mensajero;
import com.backend.model.Paquete;
import com.backend.model.Solicitud;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.PriorityQueue;

@Service
public class SolicitudServiceImpl implements SolicitudService {
//    private final SolicitudService solicitudService;
//
//    public SolicitudServiceImpl(SolicitudService solicitudService) {
//        this.solicitudService = solicitudService;
//    }

    //toda la cola de solicitudes
    @Override
    public PriorityQueue<Solicitud> obtenerSolicitudes() {
        return ColaPriori.colaPriori;
    }

    @Override
    public Solicitud  procesar1Solicitud(){

        Solicitud solicitud = ColaPriori.colaPriori.peek();
        if (SepuedeProcesarSoli(solicitud)){
            //si se procesa, entonces modifico los objetos correspondientes
            CentroService centroService = new CentroServiceImpl();
            RutaService rutaService = new RutaServiceImpl();
            PaqueteService paqueteService = new PaqueteServiceImpl();
            MensajeroService mensajeroService = new MensajeroServiceImpl();

            Paquete paqueteAEnviar = paqueteService.obtenerPaquetePorId(solicitud.getPaquete_a_enviar());
            CentroDistribucion centroDestino = centroService.obtenerCentroPorId(paqueteAEnviar.getDestino());
            CentroDistribucion ceentroOrigen = centroService.obtenerCentroPorId(paqueteAEnviar.getCentro_actual());

            ArrayList<Mensajero> obteMensajerosDelCentro  = centroService.listarMensajerosCentro(ceentroOrigen.getIdcentro());
            //si no hay mensajeros marcados como disponibles en ese centro no se realiza nada


            for (Mensajero mensajero : obteMensajerosDelCentro) {
                if (mensajero.getEstado().equals("DISPONIBLE")) {
                    mensajero.setEstado("EN_TRANSITO");
                    mensajero.setEstado("DISPONIBLE");
                    break;
                }
            }
            ceentroOrigen.setCapacidad_libre(ceentroOrigen.getCapacidad_libre()+1);
            centroDestino.setCapacidad_libre(centroDestino.getCapacidad_libre()-1);
            //cambio cosas del paquete
            paqueteAEnviar.setEstado("EN_TRANSITO");
            paqueteAEnviar.setEstado("ENTREGADO");
            paqueteAEnviar.setCentro_actual(centroDestino.getIdcentro());
            paqueteAEnviar.setDestino("ENTREGADO");

            //y luego retorno la solicitud procesada y la elimino de mi cola
            return  ColaPriori.colaPriori.poll();

        }return null;

    }
    @Override
    public boolean eliminarSolicitud(String id){
        Iterator<Solicitud> nodo= ColaPriori.colaPriori.iterator();
        while (nodo.hasNext()) {
            Solicitud solicitud =nodo.next();
            //como en las estructuras de colas en el primer proyecto
            //reviso los nodos y sus apuntadores hasta encontrar uno por id
            if (solicitud.getIde().equals(id)) {
                //si lo encuentro lo elimino
                nodo.remove();
                return true;
            }
        }
        return false;
    }

    @Override
    public Solicitud obtenerSolicitudPorId(String id) {
        Iterator<Solicitud> nodo= ColaPriori.colaPriori.iterator();
        while (nodo.hasNext()) {
            Solicitud solicitud =nodo.next();
            //como en las estructuras de colas en el primer proyecto
            //reviso los nodos y sus apuntadores hasta encontrar uno por id
            if (solicitud.getIde().equals(id)) {
                return solicitud;
            }
        }
        return null;
    }


    @Override
    public Boolean SepuedeProcesarSoli(Solicitud solicitud) {
        CentroService centroService = new CentroServiceImpl();
        RutaService rutaService = new RutaServiceImpl();
        PaqueteService paqueteService = new PaqueteServiceImpl();
        MensajeroService mensajeroService = new MensajeroServiceImpl();

        Paquete paqueteAEnviar = paqueteService.obtenerPaquetePorId(solicitud.getPaquete_a_enviar());

        if (paqueteAEnviar != null) {
            if (paqueteAEnviar.getEstado().equals("PENDIENTE")) {
                if (rutaService.EncontrarPorSuRuta(paqueteAEnviar.getCentro_actual(),paqueteAEnviar.getDestino()) != null){
                    //si no es nula esporque encontró esa ruta si se puede llevar a cabo el traspase
                    CentroDistribucion centroDestino = centroService.obtenerCentroPorId(paqueteAEnviar.getDestino());
                    CentroDistribucion ceentroOrigen = centroService.obtenerCentroPorId(paqueteAEnviar.getCentro_actual());

                    if (centroDestino.getCapacidad_libre()>0){
                        ArrayList<Mensajero> obteMensajerosDelCentro  = centroService.listarMensajerosCentro(ceentroOrigen.getIdcentro());
                        //si no hay mensajeros marcados como disponibles en ese centro no se realiza nada
                        for (Mensajero mensajero : obteMensajerosDelCentro) {
                            if (mensajero.getEstado().equals("DISPONIBLE")) {
                                return true;
                            }
                        }return false;
                    }return false;
                }return false;
            }
            return false;
        }
        return false;
    }
}
