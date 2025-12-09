package com.example.demo.controladores;

import com.example.demo.entidades.RequerimientoProyecto;
import com.example.demo.servicios.RequerimientoProyectoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

/**
 * Controlador REST para gestionar las operaciones de RequerimientoProyecto.
 * Expone endpoints para crear, leer y consultar requerimientos de proyectos.
 * Ruta base: /requerimiento-proyecto
 * 
 * @author NGINX
 * @version 1.0
 */
@RestController
@RequestMapping("/requerimiento-proyecto")
public class RequerimientoProyectoController {
    
    @Autowired
    private RequerimientoProyectoService requerimientoService;

    /**
     * Obtiene la lista de todos los requerimientos.
     * Endpoint: GET /requerimiento-proyecto
     * @return Lista de requerimientos
     */
    @GetMapping()
    public ArrayList<RequerimientoProyecto> obtenerRequerimientos(){
        return this.requerimientoService.obtenerRequerimientos();
    }

    /**
     * Crea o actualiza un requerimiento.
     * Endpoint: POST /requerimiento-proyecto
     * @param requerimiento Datos del requerimiento en formato JSON
     * @return Requerimiento guardado con su ID generado
     */
    @PostMapping()
    public RequerimientoProyecto guardarRequerimiento(@RequestBody RequerimientoProyecto requerimiento){
        return this.requerimientoService.guardarRequerimiento(requerimiento);
    }

    /**
     * Obtiene un requerimiento específico por su ID.
     * Endpoint: GET /requerimiento-proyecto/{id}
     * @param id ID del requerimiento a buscar
     * @return Optional con el requerimiento si existe
     */
    @GetMapping(path = "/{id}")
    public Optional<RequerimientoProyecto> obtenerRequerimientoPorId(@PathVariable("id") int id){
        return this.requerimientoService.obtenerPorId(id);
    }

    /**
     * Obtiene todos los requerimientos asociados a un proyecto específico.
     * Endpoint: GET /requerimiento-proyecto/proyecto/{idProyecto}
     * @param idProyecto ID del proyecto
     * @return Lista de requerimientos del proyecto
     */
    @GetMapping("/proyecto/{idProyecto}")
    public ArrayList<RequerimientoProyecto> obtenerRequerimientosPorProyecto(@PathVariable("idProyecto") int idProyecto){
        return this.requerimientoService.obtenerPorProyecto(idProyecto);
    }

    /**
     * Obtiene todos los requerimientos asociados a un sprint específico.
     * Endpoint: GET /requerimiento-proyecto/sprint/{idSprint}
     * @param idSprint ID del sprint
     * @return Lista de requerimientos del sprint
     */
    @GetMapping("/sprint/{idSprint}")
    public ArrayList<RequerimientoProyecto> obtenerRequerimientosPorSprint(@PathVariable("idSprint") int idSprint){
        return this.requerimientoService.obtenerPorSprint(idSprint);
    }

    /**
     * Actualiza un requerimiento existente.
     * Endpoint: PUT /requerimiento-proyecto/{id}
     * @param id ID del requerimiento a actualizar
     * @param requerimiento Datos actualizados del requerimiento
     * @return Requerimiento actualizado
     */
    @PutMapping(path = "/{id}")
    public RequerimientoProyecto actualizarRequerimiento(@PathVariable("id") int id, @RequestBody RequerimientoProyecto requerimiento){
        requerimiento.setIdRequerimiento(id);
        return this.requerimientoService.guardarRequerimiento(requerimiento);
    }
}
