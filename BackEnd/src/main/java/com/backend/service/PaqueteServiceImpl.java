package com.backend.service;
import com.backend.lista.Listas;
import com.backend.model.Paquete;
import org.springframework.stereotype.Service;
import java.util.ArrayList;


@Service
public class PaqueteServiceImpl implements PaqueteService{
    //final private ArrayList<Paquete> lista_paquetes = new ArrayList<>();

    public PaqueteServiceImpl() {
        //constructor incial
    }


    //metodo para devolver la lista de los paquetes
    @Override
    public ArrayList<Paquete> obtenerPaquetes() {
        return Listas.listaPaquetes;
    }
}
