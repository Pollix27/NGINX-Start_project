package com.example.demo.controladores;


import com.example.demo.entidades.Cliente;
import com.example.demo.servicios.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping("/cliente")
public class ClienteController {
    @Autowired
    private ClienteService clienteService;

    @GetMapping()
    public ArrayList<Cliente> obtenerClientes(){
        return this.clienteService.obtenerCliente();
    }

    @PostMapping()
    public Cliente guardarCliente(@RequestBody Cliente Cliente){
        return this.clienteService.guardarCliente(Cliente);
    }

    @GetMapping(path = "/{id}")
    public Optional<Cliente> obtenerClientePorId(@PathVariable("id") int id){
        return this.clienteService.obtenerPorId(id);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<String> eliminarPorId(@PathVariable("id") int id){
        boolean ok = this.clienteService.eliminarCliente(id);
        if(ok){
            return ResponseEntity.ok("Se eliminó el Cliente con id " + id);
        }else{
            return ResponseEntity.notFound().build();
        }
    }

}