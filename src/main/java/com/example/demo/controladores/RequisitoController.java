package com.example.demo.controladores;

import com.example.demo.entidades.Requisito;
import com.example.demo.servicios.RequisitoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/requisitos")
@CrossOrigin(origins = "*")
public class RequisitoController {
    
    @Autowired
    private RequisitoService servicio;
    
    @GetMapping
    public List<Requisito> listarTodos() {
        return servicio.listarTodos();
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Requisito> buscarPorId(@PathVariable int id) {
        return servicio.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    @PostMapping
    public Requisito crear(@RequestBody Requisito requisito) {
        return servicio.guardar(requisito);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable int id, @RequestBody Requisito requisito) {
        try {
            if (requisito == null) {
                return ResponseEntity.badRequest().body("Datos del requisito incompletos");
            }
            return servicio.buscarPorId(id)
                    .map(r -> {
                        requisito.setIdRequisitos(id);
                        return ResponseEntity.ok(servicio.guardar(requisito));
                    })
                    .orElse(ResponseEntity.notFound().build());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al actualizar requisito: " + e.getMessage());
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
