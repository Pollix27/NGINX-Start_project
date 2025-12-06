package com.example.demo.controladores;

import com.example.demo.entidades.Proyecto;
import com.example.demo.servicios.ProyectoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping("/proyecto")
public class ProyectoController {
    @Autowired
    private ProyectoService proyectoService;

    @GetMapping()
    public ArrayList<Proyecto> obtenerProyectos(){
        return this.proyectoService.obtenerProyecto();
    }

    @PostMapping()
    public Proyecto guardarProyecto(@RequestBody Proyecto proyecto){
        return this.proyectoService.guardarProyecto(proyecto);
    }

    @GetMapping(path = "/{id}")
    public Optional<Proyecto> obtenerProyectoPorId(@PathVariable("id") int id){
        return this.proyectoService.obtenerPorId(id);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<String> eliminarPorId(@PathVariable("id") int id){
        boolean ok = this.proyectoService.eliminarProyecto(id);
        if(ok){
            return ResponseEntity.ok("Se eliminó el Proyecto con id " + id);
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/cliente/{clienteId}")
    public ArrayList<Proyecto> obtenerProyectosPorCliente(@PathVariable("clienteId") int clienteId){
        return this.proyectoService.obtenerPorCliente(clienteId);
    }
}
