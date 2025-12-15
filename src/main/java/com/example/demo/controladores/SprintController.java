package com.example.demo.controladores;

import com.example.demo.entidades.Sprint;
import com.example.demo.servicios.SprintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * Controlador REST para gestionar operaciones CRUD de sprints.
 */
@RestController
@RequestMapping("/api/sprints")
@CrossOrigin(origins = "*")
public class SprintController {
    
    @Autowired
    private SprintService servicio;
    
    /**
     * Obtiene la lista de todos los sprints.
     * @return Lista de sprints
     */
    @GetMapping
    public List<Sprint> listarTodos() {
        return servicio.listarTodos();
    }
    
    /**
     * Busca un sprint por su ID.
     * @param id Identificador del sprint
     * @return ResponseEntity con el sprint o 404 si no existe
     */
    @GetMapping("/{id}")
    public ResponseEntity<Sprint> buscarPorId(@PathVariable int id) {
        return servicio.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    /**
     * Crea un nuevo sprint.
     * @param sprint Sprint a crear
     * @return ResponseEntity con el sprint creado o error
     */
    @PostMapping
    public ResponseEntity<?> crear(@RequestBody Sprint sprint) {
        try {
            if (sprint == null) {
                return ResponseEntity.badRequest().body("Datos del sprint incompletos");
            }
            Sprint nuevo = servicio.guardar(sprint);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevo);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al crear sprint: " + e.getMessage());
        }
    }
    
    /**
     * Actualiza un sprint existente.
     * @param id Identificador del sprint
     * @param sprint Datos actualizados del sprint
     * @return ResponseEntity con el sprint actualizado o 404 si no existe
     */
    @PutMapping("/{id}")
    public ResponseEntity<Sprint> actualizar(@PathVariable int id, @RequestBody Sprint sprint) {
        return servicio.buscarPorId(id)
                .map(s -> {
                    sprint.setIdSprint(id);
                    return ResponseEntity.ok(servicio.guardar(sprint));
                })
                .orElse(ResponseEntity.notFound().build());
    }
    
    /**
     * Elimina un sprint por su ID.
     * @param id Identificador del sprint a eliminar
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
