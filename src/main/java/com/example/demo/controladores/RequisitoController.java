package com.example.demo.controladores;

import com.example.demo.entidades.Requisito;
import com.example.demo.servicios.RequisitoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * Controlador REST para gestionar operaciones CRUD de requisitos.
 */
@RestController
@RequestMapping("/api/requisitos")
@CrossOrigin(origins = "*")
public class RequisitoController {
    
    @Autowired
    private RequisitoService servicio;
    
    /**
     * Obtiene la lista de todos los requisitos.
     * @return Lista de requisitos
     */
    @GetMapping
    public List<Requisito> listarTodos() {
        return servicio.listarTodos();
    }
    
    /**
     * Busca un requisito por su ID.
     * @param id Identificador del requisito
     * @return ResponseEntity con el requisito o 404 si no existe
     */
    @GetMapping("/{id}")
    public ResponseEntity<Requisito> buscarPorId(@PathVariable int id) {
        return servicio.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    /**
     * Crea un nuevo requisito.
     * @param requisito Requisito a crear
     * @return Requisito creado
     */
    @PostMapping
    public Requisito crear(@RequestBody Requisito requisito) {
        return servicio.guardar(requisito);
    }
    
    /**
     * Actualiza un requisito existente.
     * @param id Identificador del requisito
     * @param requisito Datos actualizados del requisito
     * @return ResponseEntity con el requisito actualizado, 404 si no existe o 500 si hay error
     */
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
    
    /**
     * Elimina un requisito por su ID.
     * @param id Identificador del requisito a eliminar
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
