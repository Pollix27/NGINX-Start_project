package com.example.demo.controladores;

import com.example.demo.entidades.Presupuesto;
import com.example.demo.servicios.PresupuestoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/presupuestos")
@CrossOrigin(origins = "*")
public class PresupuestoController {
    
    @Autowired
    private PresupuestoService servicio;
    
    @GetMapping
    public List<Presupuesto> listarTodos() {
        return servicio.listarTodos();
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Presupuesto> buscarPorId(@PathVariable int id) {
        return servicio.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
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
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable int id) {
        if (servicio.buscarPorId(id).isPresent()) {
            servicio.eliminar(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
