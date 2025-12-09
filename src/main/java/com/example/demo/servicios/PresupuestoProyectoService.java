package com.example.demo.servicios;

import com.example.demo.entidades.PresupuestoProyecto;
import com.example.demo.interfaces.IRPresupuestoProyecto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

/**
 * Servicio para la lógica de negocio de PresupuestoProyecto.
 * Gestiona las operaciones relacionadas con los presupuestos de proyectos.
 * 
 * @author NGINX
 * @version 1.0
 */
@Service
public class PresupuestoProyectoService {
    
    @Autowired
    IRPresupuestoProyecto presupuestoRepository;

    /**
     * Obtiene todos los presupuestos registrados.
     * @return Lista de todos los presupuestos
     */
    public ArrayList<PresupuestoProyecto> obtenerPresupuestos(){
        return (ArrayList<PresupuestoProyecto>) presupuestoRepository.findAll();
    }

    /**
     * Guarda o actualiza un presupuesto en la base de datos.
     * @param presupuesto Presupuesto a guardar
     * @return Presupuesto guardado con su ID generado
     */
    public PresupuestoProyecto guardarPresupuesto(PresupuestoProyecto presupuesto){
        return presupuestoRepository.save(presupuesto);
    }

    /**
     * Busca un presupuesto por su ID.
     * @param id ID del presupuesto a buscar
     * @return Optional con el presupuesto si existe, vacío si no
     */
    public Optional<PresupuestoProyecto> obtenerPorId(int id){
        return presupuestoRepository.findById(id);
    }

    /**
     * Obtiene todos los presupuestos asociados a un proyecto específico.
     * @param idProyecto ID del proyecto
     * @return Lista de presupuestos del proyecto
     */
    public ArrayList<PresupuestoProyecto> obtenerPorProyecto(int idProyecto){
        return presupuestoRepository.findByProyectoIdProyecto(idProyecto);
    }
}
