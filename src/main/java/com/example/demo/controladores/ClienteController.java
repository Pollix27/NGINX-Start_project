package com.example.demo.controladores;

import com.example.demo.entidades.Cliente;
import com.example.demo.servicios.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * Controlador REST para gestionar operaciones CRUD de clientes.
 */
@RestController
@RequestMapping("/api/clientes")
@CrossOrigin(origins = "*")
public class ClienteController {
    
    @Autowired
    private ClienteService servicio;
    
    /**
     * Obtiene la lista de todos los clientes.
     * @return Lista de clientes
     */
    @GetMapping
    public List<Cliente> listarTodos() {
        return servicio.listarTodos();
    }
    
    /**
     * Busca un cliente por su ID.
     * @param id Identificador del cliente
     * @return ResponseEntity con el cliente o 404 si no existe
     */
    @GetMapping("/{id}")
    public ResponseEntity<Cliente> buscarPorId(@PathVariable int id) {
        return servicio.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    /**
     * Crea un nuevo cliente.
     * @param cliente Cliente a crear
     * @return Cliente creado
     */
    @PostMapping
    public Cliente crear(@RequestBody Cliente cliente) {
        return servicio.guardar(cliente);
    }
    
    /**
     * Actualiza un cliente existente.
     * @param id Identificador del cliente
     * @param cliente Datos actualizados del cliente
     * @return ResponseEntity con el cliente actualizado o 404 si no existe
     */
    @PutMapping("/{id}")
    public ResponseEntity<Cliente> actualizar(@PathVariable int id, @RequestBody Cliente cliente) {
        if (!servicio.buscarPorId(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        cliente.setIdCliente(id);
        return ResponseEntity.ok(servicio.guardar(cliente));
    }
    
    /**
     * Elimina un cliente por su ID.
     * @param id Identificador del cliente a eliminar
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
