package com.example.demo.servicios;

import com.example.demo.entidades.DetallePresupuesto;
import com.example.demo.interfaces.IRDetallePresupuesto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Optional;

/**
 * Servicio para la lógica de negocio de DetallesDePresupuestos.
 * Gestiona las operaciones relacionadas con los detalles de presupuestos.
 * 
 * @author NGINX
 * @version 1.0
 */
@Service
public class DetallesDePresupuestosService {
    /** Repositorio de detalles de presupuestos inyectado automáticamente */
    @Autowired
    IRDetallePresupuesto detallesRepository;

    /**
     * Obtiene todos los detalles de presupuestos registrados.
     * @return Lista de todos los detalles de presupuestos
     */
    public ArrayList<DetallePresupuesto> obtenerDetalles(){
        return (ArrayList<DetallePresupuesto>) detallesRepository.findAll();
    }

    /**
     * Guarda o actualiza un detalle de presupuesto en la base de datos.
     * @param detalle Detalle de presupuesto a guardar
     * @return Detalle guardado con su ID generado
     */
    public DetallePresupuesto guardarDetalle(DetallePresupuesto detalle){
        return detallesRepository.save(detalle);
    }

    /**
     * Busca un detalle de presupuesto por su ID.
     * @param id ID del detalle a buscar
     * @return Optional con el detalle si existe, vacío si no
     */
    public Optional<DetallePresupuesto> obtenerPorId(int id){
        return detallesRepository.findById(id);
    }

    /**
     * Obtiene todos los detalles asociados a un presupuesto específico.
     * @param idPresupuesto ID del presupuesto
     * @return Lista de detalles del presupuesto
     */
    public ArrayList<DetallePresupuesto> obtenerPorPresupuesto(int idPresupuesto){
        return detallesRepository.findByPresupuestoIdPresupuesto(idPresupuesto);
    }
}
