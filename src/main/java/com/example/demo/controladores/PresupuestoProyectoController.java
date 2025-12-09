package com.example.demo.controladores;

import com.example.demo.entidades.PresupuestoProyecto;
import com.example.demo.servicios.PresupuestoProyectoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

/**
 * Controlador REST para gestionar las operaciones de PresupuestoProyecto.
 * Expone endpoints para crear, leer y consultar presupuestos de proyectos.
 * Ruta base: /presupuesto-proyecto
 * 
 * @author NGINX
 * @version 1.0
 */
@RestController
@RequestMapping("/presupuesto-proyecto")
public class PresupuestoProyectoController {
    
    @Autowired
    private PresupuestoProyectoService presupuestoService;

    /**
     * Obtiene la lista de todos los presupuestos.
     * Endpoint: GET /presupuesto-proyecto
     * @return Lista de presupuestos
     */
    @GetMapping()
    public ArrayList<PresupuestoProyecto> obtenerPresupuestos(){
        return this.presupuestoService.obtenerPresupuestos();
    }

    /**
     * Crea o actualiza un presupuesto.
     * Endpoint: POST /presupuesto-proyecto
     * @param presupuesto Datos del presupuesto en formato JSON
     * @return Presupuesto guardado con su ID generado
     */
    @PostMapping()
    public PresupuestoProyecto guardarPresupuesto(@RequestBody PresupuestoProyecto presupuesto){
        return this.presupuestoService.guardarPresupuesto(presupuesto);
    }

    /**
     * Obtiene un presupuesto específico por su ID.
     * Endpoint: GET /presupuesto-proyecto/{id}
     * @param id ID del presupuesto a buscar
     * @return Optional con el presupuesto si existe
     */
    @GetMapping(path = "/{id}")
    public Optional<PresupuestoProyecto> obtenerPresupuestoPorId(@PathVariable("id") int id){
        return this.presupuestoService.obtenerPorId(id);
    }

    /**
     * Obtiene todos los presupuestos asociados a un proyecto específico.
     * Endpoint: GET /presupuesto-proyecto/proyecto/{idProyecto}
     * @param idProyecto ID del proyecto
     * @return Lista de presupuestos del proyecto
     */
    @GetMapping("/proyecto/{idProyecto}")
    public ArrayList<PresupuestoProyecto> obtenerPresupuestosPorProyecto(@PathVariable("idProyecto") int idProyecto){
        return this.presupuestoService.obtenerPorProyecto(idProyecto);
    }

    /**
     * Actualiza un presupuesto existente.
     * Endpoint: PUT /presupuesto-proyecto/{id}
     * @param id ID del presupuesto a actualizar
     * @param presupuesto Datos actualizados del presupuesto
     * @return Presupuesto actualizado
     */
    @PutMapping(path = "/{id}")
    public PresupuestoProyecto actualizarPresupuesto(@PathVariable("id") int id, @RequestBody PresupuestoProyecto presupuesto){
        presupuesto.setIdPresupuesto(id);
        return this.presupuestoService.guardarPresupuesto(presupuesto);
    }
}
