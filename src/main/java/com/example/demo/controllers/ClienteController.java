package com.example.demo.controllers;


import com.example.demo.models.ClienteModel;
import com.example.demo.services.ClienteService;
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
    public ArrayList<ClienteModel> obtenerClientes(){
        return this.clienteService.obtenerCliente();
    }

    @PostMapping()
    public ClienteModel guardarCliente(@RequestBody ClienteModel Cliente){
        return this.clienteService.guardarCliente(Cliente);
    }

    @GetMapping(path = "/{id}")
    public Optional<ClienteModel> obtenerClientePorId(@PathVariable("id") int id){
        return this.clienteService.obtenerPorId(id);
    }

    @GetMapping("/query")
    public ArrayList<ClienteModel> obtenerClientePorPrioridad(@RequestParam("prioridad") Integer prioridad){
        return this.clienteService.obtenerPorPrioridad(prioridad);
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