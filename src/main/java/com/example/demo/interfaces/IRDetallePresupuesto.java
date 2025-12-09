package com.example.demo.interfaces;

import com.example.demo.entidades.DetallePresupuesto;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;

/**
 * Repositorio para la entidad DetallesDePresupuestos.
 * Proporciona operaciones CRUD y consultas personalizadas.
 * 
 * @author NGINX
 * @version 1.0
 */
@Repository
public interface IRDetallePresupuesto extends CrudRepository<DetallePresupuesto, Integer> {
    
    /**
     * Busca todos los detalles asociados a un presupuesto específico.
     * @param idPresupuesto ID del presupuesto
     * @return Lista de detalles del presupuesto
     */
    ArrayList<DetallePresupuesto> findByPresupuestoIdPresupuesto(int idPresupuesto);
}
