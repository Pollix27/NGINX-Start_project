package com.example.demo.servicios;

import com.example.demo.entidades.Proyecto;
import com.example.demo.entidades.Sprint;
import com.example.demo.interfaces.IRProyecto;
import com.example.demo.interfaces.IRSprint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

/**
 * Servicio para gestionar operaciones de negocio relacionadas con sprints.
 */
@Service
public class SprintService {
    
    @Autowired
    private IRSprint repositorio;
    
    @Autowired
    private IRProyecto proyectoRepositorio;
    
    /**
     * Obtiene la lista de todos los sprints.
     * @return Lista de sprints
     */
    public List<Sprint> listarTodos() {
        return repositorio.findAll();
    }
    
    /**
     * Busca un sprint por su ID.
     * @param id Identificador del sprint
     * @return Optional con el sprint si existe
     */
    public Optional<Sprint> buscarPorId(int id) {
        return repositorio.findById(id);
    }
    
    /**
     * Guarda o actualiza un sprint.
     * Valida y asigna el proyecto correspondiente.
     * @param sprint Sprint a guardar
     * @return Sprint guardado
     */
    public Sprint guardar(Sprint sprint) {
        if (sprint == null) {
            throw new IllegalArgumentException("El sprint no puede ser nulo");
        }
        if (sprint.getProyecto() == null || sprint.getProyecto().getIdProyecto() <= 0) {
            throw new IllegalArgumentException("El sprint debe tener un proyecto válido");
        }
        Proyecto proyecto = proyectoRepositorio.findById(sprint.getProyecto().getIdProyecto())
                .orElseThrow(() -> new RuntimeException("Proyecto no encontrado con ID: " + sprint.getProyecto().getIdProyecto()));
        sprint.setProyecto(proyecto);
        return repositorio.save(sprint);
    }
    
    /**
     * Elimina un sprint por su ID.
     * @param id Identificador del sprint a eliminar
     */
    public void eliminar(int id) {
        repositorio.deleteById(id);
    }
}
