package com.example.demo.interfaces;

import com.example.demo.entidades.Recurso;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

/**
 * Repositorio para la entidad Recurso.
 * Proporciona operaciones CRUD y consultas personalizadas.
 * 
 * @author NGINX
 * @version 1.0
 */
@Repository
public interface IRRecurso extends CrudRepository<Recurso, Integer> {
    
    /**
     * Busca todos los recursos asociados a un proyecto específico.
     * Según el diagrama ER: PROYECTO ||--|{ RECURSOS (relación 1 a muchos)
     * @param idProyecto ID del proyecto
     * @return Lista de recursos del proyecto
     */
    ArrayList<Recurso> findByProyectoIdProyecto(int idProyecto);
}
