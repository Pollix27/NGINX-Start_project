package com.example.demo.servicios;

import com.example.demo.entidades.ServiciosAdicionales;
import com.example.demo.repositorios.ServiciosAdicionalesRepository;
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
    ServiciosAdicionalesRepository serviciosRepository;

    /**
     * Obtiene todos los servicios adicionales registrados.
     * @return Lista de todos los servicios adicionales
     */
    public ArrayList<ServiciosAdicionales> obtenerServicios(){
        return (ArrayList<ServiciosAdicionales>) serviciosRepository.findAll();
    }

    /**
     * Guarda o actualiza un servicio adicional en la base de datos.
     * @param servicio Servicio adicional a guardar
     * @return Servicio guardado con su ID generado
     */
    public ServiciosAdicionales guardarServicio(ServiciosAdicionales servicio){
        return serviciosRepository.save(servicio);
    }

    /**
     * Busca un servicio adicional por su ID.
     * @param id ID del servicio a buscar
     * @return Optional con el servicio si existe, vacío si no
     */
    public Optional<ServiciosAdicionales> obtenerPorId(int id){
        return serviciosRepository.findById(id);
    }

    /**
     * Obtiene todos los servicios adicionales asociados a un detalle de presupuesto.
     * @param idDetalle ID del detalle de presupuesto
     * @return Lista de servicios adicionales del detalle
     */
    public ArrayList<ServiciosAdicionales> obtenerPorDetalle(int idDetalle){
        return serviciosRepository.findByDetallePresupuestoIdDetallePresupuesto(idDetalle);
    }
}
