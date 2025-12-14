package com.example.demo.controladores;

import com.example.demo.entidades.Colaborador;
import com.example.demo.servicios.ColaboradorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/colaboradores")
@CrossOrigin(origins = "*")
public class ColaboradorController {
    
    @Autowired
    private ColaboradorService servicio;
    
    @GetMapping
    public List<Colaborador> listarTodos() {
        return servicio.listarTodos();
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Colaborador> buscarPorId(@PathVariable int id) {
        return servicio.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    @PostMapping
    public Colaborador crear(@RequestBody Colaborador colaborador) {
        return servicio.guardar(colaborador);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Colaborador> actualizar(@PathVariable int id, @RequestBody Colaborador colaborador) {
        return servicio.buscarPorId(id)
                .map(c -> {
                    colaborador.setIdColaborador(id);
                    return ResponseEntity.ok(servicio.guardar(colaborador));
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
