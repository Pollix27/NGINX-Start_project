package com.example.demo.repositorios;

import com.example.demo.entidades.ServiciosAdicionales;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;

/**
 * Repositorio para la entidad ServiciosAdicionales.
 * Proporciona operaciones CRUD y consultas personalizadas.
 * 
 * @author NGINX
 * @version 1.0
 */
@Repository
public interface ServiciosAdicionalesRepository extends CrudRepository<ServiciosAdicionales, Integer> {
    
    /**
     * Busca todos los servicios adicionales asociados a un detalle de presupuesto específico.
     * @param idDetallePresupuesto ID del detalle de presupuesto
     * @return Lista de servicios adicionales del detalle
     */
    ArrayList<ServiciosAdicionales> findByDetallePresupuestoIdDetallePresupuesto(int idDetallePresupuesto);
}
