package com.example.demo.controladores;

import com.example.demo.entidades.Colaborador;
import com.example.demo.servicios.ColaboradorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * Controlador REST para gestionar operaciones CRUD de colaboradores.
 */
@RestController
@RequestMapping("/api/colaboradores")
@CrossOrigin(origins = "*")
public class ColaboradorController {
    
    @Autowired
    private ColaboradorService servicio;
    
    /**
     * Obtiene la lista de todos los colaboradores.
     * @return Lista de colaboradores
     */
    @GetMapping
    public List<Colaborador> listarTodos() {
        return servicio.listarTodos();
    }
    
    /**
     * Busca un colaborador por su ID.
     * @param id Identificador del colaborador
     * @return ResponseEntity con el colaborador o 404 si no existe
     */
    @GetMapping("/{id}")
    public ResponseEntity<Colaborador> buscarPorId(@PathVariable int id) {
        return servicio.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    /**
     * Crea un nuevo colaborador.
     * @param colaborador Colaborador a crear
     * @return Colaborador creado
     */
    @PostMapping
    public Colaborador crear(@RequestBody Colaborador colaborador) {
        return servicio.guardar(colaborador);
    }
    
    /**
     * Actualiza un colaborador existente.
     * @param id Identificador del colaborador
     * @param colaborador Datos actualizados del colaborador
     * @return ResponseEntity con el colaborador actualizado, 404 si no existe o 500 si hay error
     */
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable int id, @RequestBody Colaborador colaborador) {
        try {
            if (colaborador == null) {
                return ResponseEntity.badRequest().body("Datos del colaborador incompletos");
            }
            return servicio.buscarPorId(id)
                    .map(c -> {
                        colaborador.setIdColaborador(id);
                        return ResponseEntity.ok(servicio.guardar(colaborador));
                    })
                    .orElse(ResponseEntity.notFound().build());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al actualizar colaborador: " + e.getMessage());
        }
    }
    
    /**
     * Elimina un colaborador por su ID.
     * @param id Identificador del colaborador a eliminar
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
