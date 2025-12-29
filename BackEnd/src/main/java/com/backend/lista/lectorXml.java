package com.backend.lista;

import com.backend.model.CentroDistribucion;
import com.backend.model.Mensajero;
import com.backend.model.Paquete;
import com.backend.model.Ruta;
import com.backend.model.Solicitud;

import com.backend.service.*;
import org.w3c.dom.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.InputStream;

import org.xml.sax.SAXParseException;

public class lectorXml {
    public void leerXml(InputStream archivoxml) throws Exception {
        //a partir del archivo xml que se le pasa como input stream lo va a intentar leer correctamente

        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document documento = builder.parse(archivoxml);

            documento.getDocumentElement().normalize();
            // se obtiene raiz y se guarda en raiz

            Element raiz = documento.getDocumentElement();

            //obtengo la etiqueta configuracion y sus etiquetas hijas
            Element configuracion = (Element) raiz.getElementsByTagName("configuracion").item(0);


            //creo una instancia d centro service para utilizar sus metodos
            CentroService centroService = new CentroServiceImpl();
            RutaService rutaService = new RutaServiceImpl();


            // leo las etiquetas centro
            //si no hay me salgo para que no haya error
            // leo las etiquetas centros

            NodeList centrosList = configuracion.getElementsByTagName("centros");
            if (centrosList.getLength() > 0) {
                Element centros = (Element) centrosList.item(0);
                NodeList centros_temp = centros.getElementsByTagName("centro");
                if (centros_temp.getLength() > 0) {



                    for (int i = 0; i < centros_temp.getLength(); i++) {
                        Element centro = (Element) centros_temp.item(i);

                        String idCentro = centro.getAttribute("id");
                        String nombreCentro = centro.getElementsByTagName("nombre").item(0).getTextContent();
                        String ciudadCentro = centro.getElementsByTagName("ciudad").item(0).getTextContent();
                        String capCentro = centro.getElementsByTagName("capacidad").item(0).getTextContent();

                        System.out.println("Centro:");
                        System.out.println(" ID: " + idCentro);
                        System.out.println(" Nombre: " + nombreCentro);
                        System.out.println(" Ciudad: " + ciudadCentro);
                        System.out.println(" Capacidad: " + capCentro+"\n");

                        //SI no existe centro en la listacentros se crea el objeto y lo guardo
                        if (centroService.obtenerCentroPorId(idCentro) == null && Integer.parseInt(capCentro) > 0 ) {
                            System.out.println(" ID: " + idCentro + "agregado correctamente\n");

                            Listas.listaCentros.add(new CentroDistribucion(idCentro,nombreCentro,ciudadCentro,capCentro));

                        } else {
                            if (Integer.parseInt(capCentro) <= 0){
                                System.out.println(" ID: " + idCentro + "tiene capacidad menor a 1\n");
                            }else {
                                //si si existe se menciona
                                System.out.println(" ID: " + idCentro + "ya existia\n");
                            }
                        }
                    }
                } else {
                    System.out.println("no existe 1 centro en la etiqueta centros en el archivo xml");
                }
            } else {
                System.out.println("no hay etiqueta centros en el archivo xml");
            }


            // leer las etiquetas rutas
            NodeList rutasList = configuracion.getElementsByTagName("rutas");
            if (rutasList.getLength() > 0) {
                Element rutas = (Element) rutasList.item(0);
                NodeList ruta_actual = rutas.getElementsByTagName("ruta");
                if (ruta_actual.getLength() > 0) {
                    for (int i = 0; i < ruta_actual.getLength(); i++) {
                        Element ruta = (Element) ruta_actual.item(i);

                        //guardo los atributos de las rutas en variables
                        String idRuta = ruta.getAttribute("id");
                        String origenRuta = ruta.getAttribute("origen");
                        String destinoRuta = ruta.getAttribute("destino");
                        String distanciaRuta = ruta.getAttribute("distancia");

                        System.out.println("Ruta:");
                        System.out.println(" ID: " + idRuta);
                        System.out.println(" Origen: " + origenRuta);
                        System.out.println(" Destino: " + destinoRuta);
                        System.out.println(" Distancia: " + distanciaRuta);

                        Ruta rutaactual= new Ruta(idRuta,origenRuta,destinoRuta,distanciaRuta);
                        //SI no existe rutasimilar o no tiene errores origendestino
                        // en la listarutas se crea el objeto y lo guardo\
                        if (!rutaService.existeRutaSimilar(rutaactual)) {
                            System.out.println("ruta "+ rutaactual.getIde() + " agregada correctamente\n");
                            Listas.listaRutas.add(rutaactual);
                        }


                    }
                } else {
                    System.out.println("No hay rutas en el archivo xml");
                }
            } else {
                System.out.println("No hay etiqueta rutas en el archivo xml");
            }

            // leer mensajeros y mostrar error si no encuentro etiquetas
            NodeList mensajerosList = configuracion.getElementsByTagName("mensajeros");
            if (mensajerosList.getLength() > 0) {
                Element mensajeros = (Element) mensajerosList.item(0);
                NodeList mensajero_actual = mensajeros.getElementsByTagName("mensajero");
                if (mensajero_actual.getLength() > 0) {
                    for (int i = 0; i < mensajero_actual.getLength(); i++) {
                        Element mensajero = (Element) mensajero_actual.item(i);
                        String idMensajero = mensajero.getAttribute("id");
                        String nombreMensajero = mensajero.getAttribute("nombre");
                        String capacidadMensajero = mensajero.getAttribute("capacidad");
                        String centroMensajero = mensajero.getAttribute("centro");

                        System.out.println("Mensajero:");
                        System.out.println(" ID: " + idMensajero);
                        System.out.println(" con nombre: " + nombreMensajero);
                        System.out.println(" capacidad: " + capacidadMensajero);
                        System.out.println(" centro asignado: " + centroMensajero);

                        //aqui voy a poner  todas las validaciones para ver si se creael mensajero o no
                        Listas.listaMensajeros.add(new Mensajero(idMensajero,nombreMensajero,capacidadMensajero,centroMensajero));
                        System.out.println("mensajero agregado correctamente\n");
                    }
                } else {
                    System.out.println("No hay mensajeros en el archivo xml");
                }
            } else {
                System.out.println("No hay etiqueta mensajeros en el archivo xml");
            }

            // leemos las etiquetas de paquetes que estan en la etiqueta de configuracion
            NodeList paquetesList = configuracion.getElementsByTagName("paquetes");
            if (paquetesList.getLength() > 0) {
                Element paquetes = (Element) paquetesList.item(0);
                NodeList paquete_actual = paquetes.getElementsByTagName("paquete");
                if (paquete_actual.getLength() > 0) {
                    for (int i = 0; i < paquete_actual.getLength(); i++) {
                        Element paquete = (Element) paquete_actual.item(i);

                        String idPaquete = paquete.getAttribute("id");
                        String clientePaquete = paquete.getAttribute("cliente");
                        String pesoPaquete = paquete.getAttribute("peso");
                        String destinoPaquete = paquete.getAttribute("destino");
                        String estadoPaquete = paquete.getAttribute("estado");
                        String centroActualPaquete = paquete.getAttribute("centroActual");

                        System.out.println("Paquete:");
                        System.out.println(" ID: " + idPaquete);
                        System.out.println(" Cliente: " + clientePaquete);
                        System.out.println(" Peso: " + pesoPaquete);
                        System.out.println(" Destino: " + destinoPaquete);
                        System.out.println(" Estado: " + estadoPaquete);
                        System.out.println(" Centro Actual: " + centroActualPaquete);

                        //Aqui voy a hacer las validaciones y si cumple guardo el paquete

                        for (CentroDistribucion centro : Listas.listaCentros) {
                            if (centro.getIdcentro().equals(centroActualPaquete)) {
                                centro.setCapacidad_libre(centro.getCapacidad_libre()-1);
                                break;
                            }
                        }
                        Listas.listaPaquetes.add(new Paquete(idPaquete,clientePaquete,pesoPaquete,destinoPaquete,estadoPaquete,centroActualPaquete));
                        System.out.println("paquete agregado correctamente\n");
                    }
                } else {
                    System.out.println("No hay paquetes en el archivo xml");
                }
            } else {
                System.out.println("No hay etiqueta paquetes en el archivo xml");
            }

            // leer las solicitudes, hay que comprobar que se pueda crear cada objeto aqui dentro
            NodeList solicitudesList = configuracion.getElementsByTagName("solicitudes");
            if (solicitudesList.getLength() > 0) {
                Element solicitudes = (Element) solicitudesList.item(0);
                NodeList solicitud_actual = solicitudes.getElementsByTagName("solicitud");
                if (solicitud_actual.getLength() > 0) {
                    for (int i = 0; i < solicitud_actual.getLength(); i++) {
                        Element solicitud = (Element) solicitud_actual.item(i);

                        String id =  solicitud.getAttribute("id");
                        String tipo = (String) solicitud.getAttribute("tipo");
                        String paqueteAenviar = (String) solicitud.getAttribute("paquete");
                        String prioridad =  solicitud.getAttribute("prioridad");

                        ColaPriori.colaPriori.add((new Solicitud(id, tipo, paqueteAenviar, prioridad)));
                        System.out.println("Solicitud:");
                        System.out.println(" ID: " + id);
                        System.out.println(" Tipo: " + tipo);
                        System.out.println(" Paquete a enviar: " + paqueteAenviar);
                        System.out.println(" Prioridad: " + prioridad);
                    }
                } else {
                    System.out.println("No hay solicitudes en el archivo xml");
                }
            } else {
                System.out.println("No hay etiqueta solicitudes en el archivo xml");
            }
        } catch (SAXParseException e) {
            System.out.println("error al terminar etiquetas: " + e.getMessage());
            //  error al ver que no hay etiqueta terminada igual
        } catch (Exception e) {
            System.out.println("error inesperado: " + e.getMessage());
            // 
        }

    }



}
