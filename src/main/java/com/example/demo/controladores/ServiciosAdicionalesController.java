package com.example.demo.controladores;

import com.example.demo.entidades.ServiciosAdicionales;
import com.example.demo.servicios.ServiciosAdicionalesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.Optional;

/**
 * Controlador REST para gestionar las operaciones de ServiciosAdicionales.
 * Expone endpoints para crear, leer y consultar servicios adicionales.
 * Ruta base: /servicios-adicionales
 * 
 * @author NGINX
 * @version 1.0
 */
@RestController
@RequestMapping("/servicios-adicionales")
public class ServiciosAdicionalesController {
    /** Servicio de servicios adicionales inyectado automáticamente */
    @Autowired
    private ServiciosAdicionalesService serviciosService;

    /**
     * Obtiene la lista de todos los servicios adicionales.
     * Endpoint: GET /servicios-adicionales
     * @return Lista de servicios adicionales
     */
    @GetMapping()
    public ArrayList<ServiciosAdicionales> obtenerServicios(){
        return this.serviciosService.obtenerServicios();
    }

    /**
     * Crea o actualiza un servicio adicional.
     * Endpoint: POST /servicios-adicionales
     * @param servicio Datos del servicio en formato JSON
     * @return Servicio guardado con su ID generado
     */
    @PostMapping()
    public ServiciosAdicionales guardarServicio(@RequestBody ServiciosAdicionales servicio){
        return this.serviciosService.guardarServicio(servicio);
    }

    /**
     * Obtiene un servicio adicional específico por su ID.
     * Endpoint: GET /servicios-adicionales/{id}
     * @param id ID del servicio a buscar
     * @return Optional con el servicio si existe
     */
    @GetMapping(path = "/{id}")
    public Optional<ServiciosAdicionales> obtenerServicioPorId(@PathVariable("id") int id){
        return this.serviciosService.obtenerPorId(id);
    }

    /**
     * Obtiene todos los servicios adicionales asociados a un detalle de presupuesto.
     * Endpoint: GET /servicios-adicionales/detalle/{idDetalle}
     * @param idDetalle ID del detalle de presupuesto
     * @return Lista de servicios adicionales del detalle
     */
    @GetMapping("/detalle/{idDetalle}")
    public ArrayList<ServiciosAdicionales> obtenerServiciosPorDetalle(@PathVariable("idDetalle") int idDetalle){
        return this.serviciosService.obtenerPorDetalle(idDetalle);
    }
}
