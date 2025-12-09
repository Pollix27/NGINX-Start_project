package com.example.demo.servicios;

import com.example.demo.entidades.Recurso;
import com.example.demo.interfaces.IRRecurso;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

/**
 * Servicio para la lógica de negocio de Recurso.
 * Gestiona las operaciones relacionadas con los recursos de proyectos.
 * 
 * @author NGINX
 * @version 1.0
 */
@Service
public class RecursoService {
    
    @Autowired
    IRRecurso recursoRepository;

    /**
     * Obtiene todos los recursos registrados.
     * @return Lista de todos los recursos
     */
    public ArrayList<Recurso> obtenerRecursos(){
        return (ArrayList<Recurso>) recursoRepository.findAll();
    }

    /**
     * Guarda o actualiza un recurso en la base de datos.
     * @param recurso Recurso a guardar
     * @return Recurso guardado con su ID generado
     */
    public Recurso guardarRecurso(Recurso recurso){
        return recursoRepository.save(recurso);
    }

    /**
     * Busca un recurso por su ID.
     * @param id ID del recurso a buscar
     * @return Optional con el recurso si existe, vacío si no
     */
    public Optional<Recurso> obtenerPorId(int id){
        return recursoRepository.findById(id);
    }

    /**
     * Obtiene todos los recursos asociados a un proyecto específico.
     * @param idProyecto ID del proyecto
     * @return Lista de recursos del proyecto
     */
    public ArrayList<Recurso> obtenerPorProyecto(int idProyecto){
        return recursoRepository.findByProyectoIdProyecto(idProyecto);
    }
}
