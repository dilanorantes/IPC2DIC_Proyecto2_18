package com.backend.lista;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.InputStream;

public class lectorXml {
    public void leerXml(InputStream archivoxml) throws Exception {
        //a partir del archivo xml que se le pasa como input stream lo va a intentar leer correctamente

        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(archivoxml);

            // se obtiene raiz y se guarda en raiz

            Element raiz = document.getDocumentElement();

            Element configuracion = (Element) raiz.getElementsByTagName("configuracion").item(0);

            // leo las etiquetas centro
            //si no hay me salgo para que no haya error
            // leo las etiquetas centros

            NodeList centrosList = configuracion.getElementsByTagName("centros");
            if (centrosList.getLength() > 0) {
                Element centros = (Element) centrosList.item(0);
                NodeList centro_actual = centros.getElementsByTagName("centro");
                if (centro_actual.getLength() > 0) {
                    for (int i = 0; i < centro_actual.getLength(); i++) {
                        Element centro = (Element) centro_actual.item(i);
                        System.out.println("Centro:");
                        System.out.println("  ID: " + centro.getAttribute("id"));
                        System.out.println("  Nombre: " + centro.getElementsByTagName("nombre").item(0).getTextContent());
                        System.out.println("  Ciudad: " + centro.getElementsByTagName("ciudad").item(0).getTextContent());
                        System.out.println("  Capacidad: " + centro.getElementsByTagName("capacidad").item(0).getTextContent());
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
                        System.out.println("Ruta:");
                        System.out.println("  ID: " + ruta.getAttribute("id"));
                        System.out.println("  Origen: " + ruta.getAttribute("origen"));
                        System.out.println("  Destino: " + ruta.getAttribute("destino"));
                        System.out.println("  Distancia: " + ruta.getAttribute("distancia"));
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
                        System.out.println("Mensajero:");
                        System.out.println("  ID: " + mensajero.getAttribute("id"));
                        System.out.println("  Nombre: " + mensajero.getAttribute("nombre"));
                        System.out.println("  Capacidad: " + mensajero.getAttribute("capacidad"));
                        System.out.println("  Centro: " + mensajero.getAttribute("centro"));
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
                        System.out.println("Paquete:");
                        System.out.println("  ID: " + paquete.getAttribute("id"));
                        System.out.println("  Cliente: " + paquete.getAttribute("cliente"));
                        System.out.println("  Peso: " + paquete.getAttribute("peso"));
                        System.out.println("  Destino: " + paquete.getAttribute("destino"));
                        System.out.println("  Estado: " + paquete.getAttribute("estado"));
                        System.out.println("  Centro Actual: " + paquete.getAttribute("centroActual"));
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
                        System.out.println("Solicitud:");
                        System.out.println("  ID: " + solicitud.getAttribute("id"));
                        System.out.println("  Tipo: " + solicitud.getAttribute("tipo"));
                        System.out.println("  Paquete: " + solicitud.getAttribute("paquete"));
                        System.out.println("  Prioridad: " + solicitud.getAttribute("prioridad"));
                    }
                } else {
                    System.out.println("No hay solicitudes en el archivo xml");
                }
            } else {
                System.out.println("No hay etiqueta solicitudes en el archivo xml");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}
