package com.example.demo.servicios;

import com.example.demo.entidades.RequerimientoProyecto;
import com.example.demo.interfaces.IRRequerimientoProyecto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

/**
 * Servicio para la lógica de negocio de RequerimientoProyecto.
 * Gestiona las operaciones relacionadas con los requerimientos de proyectos.
 * 
 * @author NGINX
 * @version 1.0
 */
@Service
public class RequerimientoProyectoService {
    
    @Autowired
    IRRequerimientoProyecto requerimientoRepository;

    /**
     * Obtiene todos los requerimientos registrados.
     * @return Lista de todos los requerimientos
     */
    public ArrayList<RequerimientoProyecto> obtenerRequerimientos(){
        return (ArrayList<RequerimientoProyecto>) requerimientoRepository.findAll();
    }

    /**
     * Guarda o actualiza un requerimiento en la base de datos.
     * @param requerimiento Requerimiento a guardar
     * @return Requerimiento guardado con su ID generado
     */
    public RequerimientoProyecto guardarRequerimiento(RequerimientoProyecto requerimiento){
        return requerimientoRepository.save(requerimiento);
    }

    /**
     * Busca un requerimiento por su ID.
     * @param id ID del requerimiento a buscar
     * @return Optional con el requerimiento si existe, vacío si no
     */
    public Optional<RequerimientoProyecto> obtenerPorId(int id){
        return requerimientoRepository.findById(id);
    }

    /**
     * Obtiene todos los requerimientos asociados a un proyecto específico.
     * @param idProyecto ID del proyecto
     * @return Lista de requerimientos del proyecto
     */
    public ArrayList<RequerimientoProyecto> obtenerPorProyecto(int idProyecto){
        return requerimientoRepository.findByProyectoIdProyecto(idProyecto);
    }

    /**
     * Obtiene todos los requerimientos asociados a un sprint específico.
     * @param idSprint ID del sprint
     * @return Lista de requerimientos del sprint
     */
    public ArrayList<RequerimientoProyecto> obtenerPorSprint(int idSprint){
        return requerimientoRepository.findBySprintIdSprint(idSprint);
    }
}
