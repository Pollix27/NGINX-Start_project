package com.example.demo.controladores;


import com.example.demo.entidades.Cliente;
import com.example.demo.servicios.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

/**
 * Controlador REST para gestionar las operaciones de Cliente.
 * Expone endpoints para crear, leer y consultar clientes.
 * Ruta base: /cliente
 * 
 * @author NGINX
 * @version 1.0
 */

@RestController
@RequestMapping("/cliente")
public class ClienteController {
    /** Servicio de clientes inyectado automáticamente */
    @Autowired
    private ClienteService clienteService;

    /**
     * Obtiene la lista de todos los clientes.
     * Endpoint: GET /cliente
     * @return Lista de clientes
     */
    @GetMapping()
    public ArrayList<Cliente> obtenerClientes(){
        return this.clienteService.obtenerCliente();
    }

    /**
     * Crea o actualiza un cliente.
     * Endpoint: POST /cliente
     * @param Cliente Datos del cliente en formato JSON
     * @return Cliente guardado con su ID generado
     */
    @PostMapping()
    public Cliente guardarCliente(@RequestBody Cliente Cliente){
        return this.clienteService.guardarCliente(Cliente);
    }

    /**
     * Obtiene un cliente específico por su ID.
     * Endpoint: GET /cliente/{id}
     * @param id ID del cliente a buscar
     * @return Optional con el cliente si existe
     */
    @GetMapping(path = "/{id}")
    public Optional<Cliente> obtenerClientePorId(@PathVariable("id") int id){
        return this.clienteService.obtenerPorId(id);
    }

    /**
     * Actualiza un cliente existente.
     * Endpoint: PUT /cliente/{id}
     * @param id ID del cliente a actualizar
     * @param cliente Datos actualizados del cliente
     * @return Cliente actualizado
     */
    @PutMapping(path = "/{id}")
    public Cliente actualizarCliente(@PathVariable("id") int id, @RequestBody Cliente cliente){
        cliente.setIdCliente(id);
        return this.clienteService.guardarCliente(cliente);
    }

}