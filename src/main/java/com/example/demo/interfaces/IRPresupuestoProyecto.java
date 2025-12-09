package com.example.demo.interfaces;

import com.example.demo.entidades.PresupuestoProyecto;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

/**
 * Repositorio para la entidad PresupuestoProyecto.
 * Proporciona operaciones CRUD y consultas personalizadas.
 * 
 * @author NGINX
 * @version 1.0
 */
@Repository
public interface IRPresupuestoProyecto extends CrudRepository<PresupuestoProyecto, Integer> {
    
    /**
     * Busca el presupuesto asociado a un proyecto específico.
     * Según el diagrama ER: PROYECTO ||--|| PRESUPUESTO_DE_PROYECTO (relación 1 a 1)
     * @param idProyecto ID del proyecto
     * @return Lista de presupuestos del proyecto
     */
    ArrayList<PresupuestoProyecto> findByProyectoIdProyecto(int idProyecto);
}
