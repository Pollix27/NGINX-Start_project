package com.example.demo.controladores;

import com.example.demo.entidades.SprintProyecto;
import com.example.demo.servicios.SprintProyectoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

/**
 * Controlador REST para gestionar las operaciones de SprintProyecto.
 * Expone endpoints para crear, leer y consultar sprints de proyectos.
 * Ruta base: /sprint-proyecto
 * 
 * @author NGINX
 * @version 1.0
 */
@RestController
@RequestMapping("/sprint-proyecto")
public class SprintProyectoController {
    
    @Autowired
    private SprintProyectoService sprintService;

    /**
     * Obtiene la lista de todos los sprints.
     * Endpoint: GET /sprint-proyecto
     * @return Lista de sprints
     */
    @GetMapping()
    public ArrayList<SprintProyecto> obtenerSprints(){
        return this.sprintService.obtenerSprints();
    }

    /**
     * Crea o actualiza un sprint.
     * Endpoint: POST /sprint-proyecto
     * @param sprint Datos del sprint en formato JSON
     * @return Sprint guardado con su ID generado
     */
    @PostMapping()
    public SprintProyecto guardarSprint(@RequestBody SprintProyecto sprint){
        return this.sprintService.guardarSprint(sprint);
    }

    /**
     * Obtiene un sprint específico por su ID.
     * Endpoint: GET /sprint-proyecto/{id}
     * @param id ID del sprint a buscar
     * @return Optional con el sprint si existe
     */
    @GetMapping(path = "/{id}")
    public Optional<SprintProyecto> obtenerSprintPorId(@PathVariable("id") int id){
        return this.sprintService.obtenerPorId(id);
    }

    /**
     * Obtiene todos los sprints asociados a un proyecto específico.
     * Endpoint: GET /sprint-proyecto/proyecto/{idProyecto}
     * @param idProyecto ID del proyecto
     * @return Lista de sprints del proyecto
     */
    @GetMapping("/proyecto/{idProyecto}")
    public ArrayList<SprintProyecto> obtenerSprintsPorProyecto(@PathVariable("idProyecto") int idProyecto){
        return this.sprintService.obtenerPorProyecto(idProyecto);
    }

    /**
     * Actualiza un sprint existente.
     * Endpoint: PUT /sprint-proyecto/{id}
     * @param id ID del sprint a actualizar
     * @param sprint Datos actualizados del sprint
     * @return Sprint actualizado
     */
    @PutMapping(path = "/{id}")
    public SprintProyecto actualizarSprint(@PathVariable("id") int id, @RequestBody SprintProyecto sprint){
        sprint.setIdSprint(id);
        return this.sprintService.guardarSprint(sprint);
    }
}
