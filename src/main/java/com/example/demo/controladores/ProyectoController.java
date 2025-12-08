package com.example.demo.controladores;

import com.example.demo.entidades.Proyecto;
import com.example.demo.servicios.ProyectoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

/**
 * Controlador REST para gestionar las operaciones de Proyecto.
 * Expone endpoints para crear, leer, actualizar y eliminar proyectos.
 * Ruta base: /proyecto
 * 
 * @author NGINX
 * @version 1.0
 */
@RestController
@RequestMapping("/proyecto")
public class ProyectoController {
    /** Servicio de proyectos inyectado automáticamente */
    @Autowired
    private ProyectoService proyectoService;

    /**
     * Obtiene la lista de todos los proyectos.
     * Endpoint: GET /proyecto
     * @return Lista de proyectos
     */
    @GetMapping()
    public ArrayList<Proyecto> obtenerProyectos(){
        return this.proyectoService.obtenerProyecto();
    }

    /**
     * Crea o actualiza un proyecto.
     * Endpoint: POST /proyecto
     * @param proyecto Datos del proyecto en formato JSON
     * @return Proyecto guardado con su ID generado
     */
    @PostMapping()
    public Proyecto guardarProyecto(@RequestBody Proyecto proyecto){
        return this.proyectoService.guardarProyecto(proyecto);
    }

    /**
     * Obtiene todos los proyectos asociados a un cliente específico.
     * Endpoint: GET /proyecto/cliente/{clienteId}
     * @param clienteId ID del cliente
     * @return Lista de proyectos del cliente
     */
    @GetMapping("/cliente/{clienteId}")
    public ArrayList<Proyecto> obtenerProyectosPorCliente(@PathVariable("clienteId") int clienteId){
        return this.proyectoService.obtenerPorCliente(clienteId);
    }

    /**
     * Actualiza un proyecto existente.
     * Endpoint: PUT /proyecto/{id}
     * @param id ID del proyecto a actualizar
     * @param proyecto Datos actualizados del proyecto
     * @return Proyecto actualizado
     */
    @PutMapping(path = "/{id}")
    public Proyecto actualizarProyecto(@PathVariable("id") int id, @RequestBody Proyecto proyecto){
        proyecto.setIdProyecto(id);
        return this.proyectoService.guardarProyecto(proyecto);
    }
}
