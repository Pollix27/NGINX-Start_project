package com.example.demo.controladores;

import com.example.demo.entidades.Recurso;
import com.example.demo.servicios.RecursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

/**
 * Controlador REST para gestionar las operaciones de Recurso.
 * Expone endpoints para crear, leer y consultar recursos de proyectos.
 * Ruta base: /recurso
 * 
 * @author NGINX
 * @version 1.0
 */
@RestController
@RequestMapping("/recurso")
public class RecursoController {
    
    @Autowired
    private RecursoService recursoService;

    /**
     * Obtiene la lista de todos los recursos.
     * Endpoint: GET /recurso
     * @return Lista de recursos
     */
    @GetMapping()
    public ArrayList<Recurso> obtenerRecursos(){
        return this.recursoService.obtenerRecursos();
    }

    /**
     * Crea o actualiza un recurso.
     * Endpoint: POST /recurso
     * @param recurso Datos del recurso en formato JSON
     * @return Recurso guardado con su ID generado
     */
    @PostMapping()
    public Recurso guardarRecurso(@RequestBody Recurso recurso){
        return this.recursoService.guardarRecurso(recurso);
    }

    /**
     * Obtiene un recurso específico por su ID.
     * Endpoint: GET /recurso/{id}
     * @param id ID del recurso a buscar
     * @return Optional con el recurso si existe
     */
    @GetMapping(path = "/{id}")
    public Optional<Recurso> obtenerRecursoPorId(@PathVariable("id") int id){
        return this.recursoService.obtenerPorId(id);
    }

    /**
     * Obtiene todos los recursos asociados a un proyecto específico.
     * Endpoint: GET /recurso/proyecto/{idProyecto}
     * @param idProyecto ID del proyecto
     * @return Lista de recursos del proyecto
     */
    @GetMapping("/proyecto/{idProyecto}")
    public ArrayList<Recurso> obtenerRecursosPorProyecto(@PathVariable("idProyecto") int idProyecto){
        return this.recursoService.obtenerPorProyecto(idProyecto);
    }

    /**
     * Actualiza un recurso existente.
     * Endpoint: PUT /recurso/{id}
     * @param id ID del recurso a actualizar
     * @param recurso Datos actualizados del recurso
     * @return Recurso actualizado
     */
    @PutMapping(path = "/{id}")
    public Recurso actualizarRecurso(@PathVariable("id") int id, @RequestBody Recurso recurso){
        recurso.setIdRecurso(id);
        return this.recursoService.guardarRecurso(recurso);
    }
}
