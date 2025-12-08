package com.example.demo.controladores;

import com.example.demo.entidades.DetallesDePresupuestos;
import com.example.demo.servicios.DetallesDePresupuestosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.Optional;

/**
 * Controlador REST para gestionar las operaciones de DetallesDePresupuestos.
 * Expone endpoints para crear, leer y consultar detalles de presupuestos.
 * Ruta base: /detalles-presupuestos
 * 
 * @author NGINX
 * @version 1.0
 */
@RestController
@RequestMapping("/detalles-presupuestos")
public class DetallesDePresupuestosController {
    /** Servicio de detalles de presupuestos inyectado automáticamente */
    @Autowired
    private DetallesDePresupuestosService detallesService;

    /**
     * Obtiene la lista de todos los detalles de presupuestos.
     * Endpoint: GET /detalles-presupuestos
     * @return Lista de detalles de presupuestos
     */
    @GetMapping()
    public ArrayList<DetallesDePresupuestos> obtenerDetalles(){
        return this.detallesService.obtenerDetalles();
    }

    /**
     * Crea o actualiza un detalle de presupuesto.
     * Endpoint: POST /detalles-presupuestos
     * @param detalle Datos del detalle en formato JSON
     * @return Detalle guardado con su ID generado
     */
    @PostMapping()
    public DetallesDePresupuestos guardarDetalle(@RequestBody DetallesDePresupuestos detalle){
        return this.detallesService.guardarDetalle(detalle);
    }

    /**
     * Obtiene un detalle de presupuesto específico por su ID.
     * Endpoint: GET /detalles-presupuestos/{id}
     * @param id ID del detalle a buscar
     * @return Optional con el detalle si existe
     */
    @GetMapping(path = "/{id}")
    public Optional<DetallesDePresupuestos> obtenerDetallePorId(@PathVariable("id") int id){
        return this.detallesService.obtenerPorId(id);
    }

    /**
     * Obtiene todos los detalles asociados a un presupuesto específico.
     * Endpoint: GET /detalles-presupuestos/presupuesto/{idPresupuesto}
     * @param idPresupuesto ID del presupuesto
     * @return Lista de detalles del presupuesto
     */
    @GetMapping("/presupuesto/{idPresupuesto}")
    public ArrayList<DetallesDePresupuestos> obtenerDetallesPorPresupuesto(@PathVariable("idPresupuesto") int idPresupuesto){
        return this.detallesService.obtenerPorPresupuesto(idPresupuesto);
    }
}
