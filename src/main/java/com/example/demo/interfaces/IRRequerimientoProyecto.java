package com.example.demo.interfaces;

import com.example.demo.entidades.RequerimientoProyecto;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

/**
 * Repositorio para la entidad RequerimientoProyecto.
 * Proporciona operaciones CRUD y consultas personalizadas.
 * 
 * @author NGINX
 * @version 1.0
 */
@Repository
public interface IRRequerimientoProyecto extends CrudRepository<RequerimientoProyecto, Integer> {
    
    /**
     * Busca todos los requerimientos asociados a un proyecto específico.
     * Según el diagrama ER: PROYECTO ||--|{ REQUERIMIENTOS_DE_PROYECTO (relación 1 a muchos)
     * @param idProyecto ID del proyecto
     * @return Lista de requerimientos del proyecto
     */
    ArrayList<RequerimientoProyecto> findByProyectoIdProyecto(int idProyecto);
    
    /**
     * Busca todos los requerimientos asociados a un sprint específico.
     * Según el diagrama ER: SPRINTS_DE_PROYECTOS ||--o{ REQUERIMIENTOS_DE_PROYECTO (relación 1 a muchos)
     * @param idSprint ID del sprint
     * @return Lista de requerimientos del sprint
     */
    ArrayList<RequerimientoProyecto> findBySprintIdSprint(int idSprint);
}
