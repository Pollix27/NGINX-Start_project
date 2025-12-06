package com.example.demo.servicios;

import com.example.demo.entidades.Proyecto;
import com.example.demo.repositorios.ProyectoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class ProyectoService {
    @Autowired
    ProyectoRepository proyectoRepository;

    public ArrayList<Proyecto> obtenerProyecto(){
        return (ArrayList<Proyecto>) proyectoRepository.findAll();
    }

    public Proyecto guardarProyecto(Proyecto proyecto){
        return proyectoRepository.save(proyecto);
    }

    public Optional<Proyecto> obtenerPorId(int id){
        return proyectoRepository.findById(id);
    }

    public boolean eliminarProyecto(int id) {
        try{
            proyectoRepository.deleteById(id);
            return true;
        }catch (Exception err){
            return false;
        }
    }

    public ArrayList<Proyecto> obtenerPorCliente(int clienteId){
        return proyectoRepository.findByClienteId(clienteId);
    }
}
