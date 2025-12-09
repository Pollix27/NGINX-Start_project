package com.example.demo.servicios;

import com.example.demo.entidades.ServicioAdicional;
import com.example.demo.interfaces.IRServicioAdicional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Optional;

/**
 * Servicio para la lógica de negocio de ServiciosAdicionales.
 * Gestiona las operaciones relacionadas con los servicios adicionales.
 * 
 * @author NGINX
 * @version 1.0
 */
@Service
public class ServiciosAdicionalesService {
    /** Repositorio de servicios adicionales inyectado automáticamente */
    @Autowired
    IRServicioAdicional serviciosRepository;

    /**
     * Obtiene todos los servicios adicionales registrados.
     * @return Lista de todos los servicios adicionales
     */
    public ArrayList<ServicioAdicional> obtenerServicios(){
        return (ArrayList<ServicioAdicional>) serviciosRepository.findAll();
    }

    /**
     * Guarda o actualiza un servicio adicional en la base de datos.
     * @param servicio Servicio adicional a guardar
     * @return Servicio guardado con su ID generado
     */
    public ServicioAdicional guardarServicio(ServicioAdicional servicio){
        return serviciosRepository.save(servicio);
    }

    /**
     * Busca un servicio adicional por su ID.
     * @param id ID del servicio a buscar
     * @return Optional con el servicio si existe, vacío si no
     */
    public Optional<ServicioAdicional> obtenerPorId(int id){
        return serviciosRepository.findById(id);
    }

    /**
     * Obtiene todos los servicios adicionales asociados a un detalle de presupuesto.
     * @param idDetalle ID del detalle de presupuesto
     * @return Lista de servicios adicionales del detalle
     */
    public ArrayList<ServicioAdicional> obtenerPorDetalle(int idDetalle){
        return serviciosRepository.findByDetallePresupuestoIdDetallePresupuesto(idDetalle);
    }
}
