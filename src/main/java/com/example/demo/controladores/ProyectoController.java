package com.example.demo.controladores;

import com.example.demo.entidades.Proyecto;
import com.example.demo.servicios.ProyectoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * Controlador REST para gestionar operaciones CRUD de proyectos.
 */
@RestController
@RequestMapping("/api/proyectos")
@CrossOrigin(origins = "*")
public class ProyectoController {
    
    @Autowired
    private ProyectoService servicio;
    
    /**
     * Obtiene la lista de todos los proyectos.
     * @return Lista de proyectos
     */
    @GetMapping
    public List<Proyecto> listarTodos() {
        return servicio.listarTodos();
    }
    
    /**
     * Busca un proyecto por su ID.
     * @param id Identificador del proyecto
     * @return ResponseEntity con el proyecto o 404 si no existe
     */
    @GetMapping("/{id}")
    public ResponseEntity<Proyecto> buscarPorId(@PathVariable int id) {
        return servicio.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    /**
     * Crea un nuevo proyecto.
     * @param proyecto Proyecto a crear
     * @return ResponseEntity con el proyecto creado o error
     */
    @PostMapping
    public ResponseEntity<?> crear(@RequestBody Proyecto proyecto) {
        try {
            if (proyecto == null || proyecto.getNombreProyecto() == null || proyecto.getCliente() == null) {
                return ResponseEntity.badRequest().body("Datos del proyecto incompletos");
            }
            Proyecto nuevo = servicio.guardar(proyecto);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevo);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al crear proyecto: " + e.getMessage());
        }
    }
    
    /**
     * Actualiza un proyecto existente.
     * @param id Identificador del proyecto
     * @param proyecto Datos actualizados del proyecto
     * @return ResponseEntity con el proyecto actualizado o 404 si no existe
     */
    @PutMapping("/{id}")
    public ResponseEntity<Proyecto> actualizar(@PathVariable int id, @RequestBody Proyecto proyecto) {
        return servicio.buscarPorId(id)
                .map(p -> {
                    proyecto.setIdProyecto(id);
                    return ResponseEntity.ok(servicio.guardar(proyecto));
                })
                .orElse(ResponseEntity.notFound().build());
    }
    
    /**
     * Elimina un proyecto por su ID.
     * @param id Identificador del proyecto a eliminar
     * @return ResponseEntity con estado 200 si se eliminó o 404 si no existe
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable int id) {
        if (servicio.buscarPorId(id).isPresent()) {
            servicio.eliminar(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
