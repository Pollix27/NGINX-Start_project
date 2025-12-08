package com.example.demo.servicios;

import com.example.demo.entidades.Proyecto;
import com.example.demo.repositorios.ProyectoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

/**
 * Servicio para la lógica de negocio de Proyecto.
 * Gestiona las operaciones relacionadas con los proyectos.
 * 
 * @author NGINX
 * @version 1.0
 */
@Service
public class ProyectoService {
    /** Repositorio de proyectos inyectado automáticamente */
    @Autowired
    ProyectoRepository proyectoRepository;

    /**
     * Obtiene todos los proyectos registrados.
     * @return Lista de todos los proyectos
     */
    public ArrayList<Proyecto> obtenerProyecto(){
        return (ArrayList<Proyecto>) proyectoRepository.findAll();
    }

    /**
     * Guarda o actualiza un proyecto en la base de datos.
     * @param proyecto Proyecto a guardar
     * @return Proyecto guardado con su ID generado
     */
    public Proyecto guardarProyecto(Proyecto proyecto){
        return proyectoRepository.save(proyecto);
    }

    /**
     * Obtiene todos los proyectos asociados a un cliente específico.
     * @param clienteId ID del cliente
     * @return Lista de proyectos del cliente
     */
    public ArrayList<Proyecto> obtenerPorCliente(int clienteId){
        return proyectoRepository.findByClienteIdCliente(clienteId);
    }
}
