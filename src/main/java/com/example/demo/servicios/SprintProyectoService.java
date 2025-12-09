package com.example.demo.servicios;

import com.example.demo.entidades.SprintProyecto;
import com.example.demo.interfaces.IRSprintProyecto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

/**
 * Servicio para la lógica de negocio de SprintProyecto.
 * Gestiona las operaciones relacionadas con los sprints de proyectos.
 * 
 * @author NGINX
 * @version 1.0
 */
@Service
public class SprintProyectoService {
    
    @Autowired
    IRSprintProyecto sprintRepository;

    /**
     * Obtiene todos los sprints registrados.
     * @return Lista de todos los sprints
     */
    public ArrayList<SprintProyecto> obtenerSprints(){
        return (ArrayList<SprintProyecto>) sprintRepository.findAll();
    }

    /**
     * Guarda o actualiza un sprint en la base de datos.
     * @param sprint Sprint a guardar
     * @return Sprint guardado con su ID generado
     */
    public SprintProyecto guardarSprint(SprintProyecto sprint){
        return sprintRepository.save(sprint);
    }

    /**
     * Busca un sprint por su ID.
     * @param id ID del sprint a buscar
     * @return Optional con el sprint si existe, vacío si no
     */
    public Optional<SprintProyecto> obtenerPorId(int id){
        return sprintRepository.findById(id);
    }

    /**
     * Obtiene todos los sprints asociados a un proyecto específico.
     * @param idProyecto ID del proyecto
     * @return Lista de sprints del proyecto
     */
    public ArrayList<SprintProyecto> obtenerPorProyecto(int idProyecto){
        return sprintRepository.findByProyectoIdProyecto(idProyecto);
    }
}
