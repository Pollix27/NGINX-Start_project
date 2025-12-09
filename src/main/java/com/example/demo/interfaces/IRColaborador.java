package com.example.demo.interfaces;

import com.example.demo.entidades.Colaborador;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

/**
 * Repositorio para la entidad Colaborador.
 * Proporciona operaciones CRUD y consultas personalizadas.
 * 
 * @author NGINX
 * @version 1.0
 */
@Repository
public interface IRColaborador extends CrudRepository<Colaborador, Integer> {
    
    /**
     * Busca todos los colaboradores asociados a un proyecto específico.
     * Según el diagrama ER: PROYECTO }|--|{ COLABORADORES (relación muchos a muchos)
     * @param idProyecto ID del proyecto
     * @return Lista de colaboradores del proyecto
     */
    ArrayList<Colaborador> findByProyectoIdProyecto(int idProyecto);
    
    /**
     * Busca todos los colaboradores asociados a un sprint específico.
     * Según el diagrama ER: SPRINTS_DE_PROYECTOS ||--|{ COLABORADORES (relación 1 a muchos)
     * @param idSprint ID del sprint
     * @return Lista de colaboradores del sprint
     */
    ArrayList<Colaborador> findBySprintIdSprint(int idSprint);
}
