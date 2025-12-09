package com.example.demo.interfaces;

import com.example.demo.entidades.SprintProyecto;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

/**
 * Repositorio para la entidad SprintProyecto.
 * Proporciona operaciones CRUD y consultas personalizadas.
 * 
 * @author NGINX
 * @version 1.0
 */
@Repository
public interface IRSprintProyecto extends CrudRepository<SprintProyecto, Integer> {
    
    /**
     * Busca todos los sprints asociados a un proyecto específico.
     * Según el diagrama ER: PROYECTO ||--|{ SPRINTS_DE_PROYECTOS (relación 1 a muchos)
     * @param idProyecto ID del proyecto
     * @return Lista de sprints del proyecto
     */
    ArrayList<SprintProyecto> findByProyectoIdProyecto(int idProyecto);
}
