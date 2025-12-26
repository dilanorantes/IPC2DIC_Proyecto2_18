package com.backend.service;
import com.backend.lista.Listas;
import com.backend.model.Paquete;
import org.springframework.stereotype.Service;
import java.util.ArrayList;


@Service
public class PaqueteServiceImpl implements PaqueteService{
    final private ArrayList<Paquete> lista_paquetes = new ArrayList<>();

    public PaqueteServiceImpl() {
        //aqui se crean todos los paquetes y se agregan a la lista
        Listas.listaPaquetes.add(new Paquete("PA001", "cLIENTE1","13", "CD002", "CD001"));
        Listas.listaPaquetes.add(new Paquete("PA002", "cliente2","16", "CD003", "CD002"));
        Listas.listaPaquetes.add(new Paquete("PA003", "cliente3","16", "CD002", "CD001"));
    }


    //metodo para devolver la lista de los paquetes
    @Override
    public ArrayList<Paquete> obtenerPaquetes() {
        return Listas.listaPaquetes;
    }
}
