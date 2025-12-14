package com.example.demo.controladores;

import com.example.demo.entidades.Sprint;
import com.example.demo.servicios.SprintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/sprints")
@CrossOrigin(origins = "*")
public class SprintController {
    
    @Autowired
    private SprintService servicio;
    
    @GetMapping
    public List<Sprint> listarTodos() {
        return servicio.listarTodos();
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Sprint> buscarPorId(@PathVariable int id) {
        return servicio.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
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
    
    @PutMapping("/{id}")
    public ResponseEntity<Sprint> actualizar(@PathVariable int id, @RequestBody Sprint sprint) {
        return servicio.buscarPorId(id)
                .map(s -> {
                    sprint.setIdSprint(id);
                    return ResponseEntity.ok(servicio.guardar(sprint));
                })
                .orElse(ResponseEntity.notFound().build());
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
