package com.example.demo.controladores;

import com.example.demo.entidades.Presupuesto;
import com.example.demo.servicios.PresupuestoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * Controlador REST para gestionar operaciones CRUD de presupuestos.
 */
@RestController
@RequestMapping("/api/presupuestos")
@CrossOrigin(origins = "*")
public class PresupuestoController {
    
    @Autowired
    private PresupuestoService servicio;
    
    /**
     * Obtiene la lista de todos los presupuestos.
     * @return Lista de presupuestos
     */
    @GetMapping
    public List<Presupuesto> listarTodos() {
        return servicio.listarTodos();
    }
    
    /**
     * Busca un presupuesto por su ID.
     * @param id Identificador del presupuesto
     * @return ResponseEntity con el presupuesto o 404 si no existe
     */
    @GetMapping("/{id}")
    public ResponseEntity<Presupuesto> buscarPorId(@PathVariable int id) {
        return servicio.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    /**
     * Crea un nuevo presupuesto.
     * @param presupuesto Presupuesto a crear
     * @return ResponseEntity con el presupuesto creado o error
     */
    @PostMapping
    public ResponseEntity<?> crear(@RequestBody Presupuesto presupuesto) {
        try {
            if (presupuesto == null) {
                return ResponseEntity.badRequest().body("Datos del presupuesto incompletos");
            }
            Presupuesto nuevo = servicio.guardar(presupuesto);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevo);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al crear presupuesto: " + e.getMessage());
        }
    }
    
    /**
     * Actualiza un presupuesto existente.
     * @param id Identificador del presupuesto
     * @param presupuesto Datos actualizados del presupuesto
     * @return ResponseEntity con el presupuesto actualizado o error
     */
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable int id, @RequestBody Presupuesto presupuesto) {
        try {
            if (presupuesto == null) {
                return ResponseEntity.badRequest().body("Datos del presupuesto incompletos");
            }
            return servicio.buscarPorId(id)
                    .map(p -> {
                        presupuesto.setIdPresupuesto(id);
                        return ResponseEntity.ok(servicio.guardar(presupuesto));
                    })
                    .orElse(ResponseEntity.notFound().build());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al actualizar presupuesto: " + e.getMessage());
        }
    }
    
    /**
     * Elimina un presupuesto por su ID.
     * @param id Identificador del presupuesto a eliminar
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
