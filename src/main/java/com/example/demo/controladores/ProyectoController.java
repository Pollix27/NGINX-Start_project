package com.example.demo.controladores;

import com.example.demo.entidades.Proyecto;
import com.example.demo.servicios.ProyectoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/proyectos")
@CrossOrigin(origins = "*")
public class ProyectoController {
    
    @Autowired
    private ProyectoService servicio;
    
    @GetMapping
    public List<Proyecto> listarTodos() {
        return servicio.listarTodos();
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Proyecto> buscarPorId(@PathVariable int id) {
        return servicio.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
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
    
    @PutMapping("/{id}")
    public ResponseEntity<Proyecto> actualizar(@PathVariable int id, @RequestBody Proyecto proyecto) {
        return servicio.buscarPorId(id)
                .map(p -> {
                    proyecto.setIdProyecto(id);
                    return ResponseEntity.ok(servicio.guardar(proyecto));
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
