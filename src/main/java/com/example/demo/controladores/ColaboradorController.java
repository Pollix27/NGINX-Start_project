package com.example.demo.controladores;

import com.example.demo.entidades.Colaborador;
import com.example.demo.servicios.ColaboradorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

/**
 * Controlador REST para gestionar las operaciones de Colaborador.
 * Expone endpoints para crear, leer y consultar colaboradores de proyectos.
 * Ruta base: /colaborador
 * 
 * @author NGINX
 * @version 1.0
 */
@RestController
@RequestMapping("/colaborador")
public class ColaboradorController {
    
    @Autowired
    private ColaboradorService colaboradorService;

    /**
     * Obtiene la lista de todos los colaboradores.
     * Endpoint: GET /colaborador
     * @return Lista de colaboradores
     */
    @GetMapping()
    public ArrayList<Colaborador> obtenerColaboradores(){
        return this.colaboradorService.obtenerColaboradores();
    }

    /**
     * Crea o actualiza un colaborador.
     * Endpoint: POST /colaborador
     * @param colaborador Datos del colaborador en formato JSON
     * @return Colaborador guardado con su ID generado
     */
    @PostMapping()
    public Colaborador guardarColaborador(@RequestBody Colaborador colaborador){
        return this.colaboradorService.guardarColaborador(colaborador);
    }

    /**
     * Obtiene un colaborador específico por su ID.
     * Endpoint: GET /colaborador/{id}
     * @param id ID del colaborador a buscar
     * @return Optional con el colaborador si existe
     */
    @GetMapping(path = "/{id}")
    public Optional<Colaborador> obtenerColaboradorPorId(@PathVariable("id") int id){
        return this.colaboradorService.obtenerPorId(id);
    }

    /**
     * Obtiene todos los colaboradores asociados a un proyecto específico.
     * Endpoint: GET /colaborador/proyecto/{idProyecto}
     * @param idProyecto ID del proyecto
     * @return Lista de colaboradores del proyecto
     */
    @GetMapping("/proyecto/{idProyecto}")
    public ArrayList<Colaborador> obtenerColaboradoresPorProyecto(@PathVariable("idProyecto") int idProyecto){
        return this.colaboradorService.obtenerPorProyecto(idProyecto);
    }

    /**
     * Obtiene todos los colaboradores asociados a un sprint específico.
     * Endpoint: GET /colaborador/sprint/{idSprint}
     * @param idSprint ID del sprint
     * @return Lista de colaboradores del sprint
     */
    @GetMapping("/sprint/{idSprint}")
    public ArrayList<Colaborador> obtenerColaboradoresPorSprint(@PathVariable("idSprint") int idSprint){
        return this.colaboradorService.obtenerPorSprint(idSprint);
    }

    /**
     * Actualiza un colaborador existente.
     * Endpoint: PUT /colaborador/{id}
     * @param id ID del colaborador a actualizar
     * @param colaborador Datos actualizados del colaborador
     * @return Colaborador actualizado
     */
    @PutMapping(path = "/{id}")
    public Colaborador actualizarColaborador(@PathVariable("id") int id, @RequestBody Colaborador colaborador){
        colaborador.setIdColaborador(id);
        return this.colaboradorService.guardarColaborador(colaborador);
    }
}
