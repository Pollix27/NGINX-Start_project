package com.example.demo.servicios;

import com.example.demo.entidades.Colaborador;
import com.example.demo.interfaces.IRColaborador;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

/**
 * Servicio para la lógica de negocio de Colaborador.
 * Gestiona las operaciones relacionadas con los colaboradores de proyectos.
 * 
 * @author NGINX
 * @version 1.0
 */
@Service
public class ColaboradorService {
    
    @Autowired
    IRColaborador colaboradorRepository;

    /**
     * Obtiene todos los colaboradores registrados.
     * @return Lista de todos los colaboradores
     */
    public ArrayList<Colaborador> obtenerColaboradores(){
        return (ArrayList<Colaborador>) colaboradorRepository.findAll();
    }

    /**
     * Guarda o actualiza un colaborador en la base de datos.
     * @param colaborador Colaborador a guardar
     * @return Colaborador guardado con su ID generado
     */
    public Colaborador guardarColaborador(Colaborador colaborador){
        return colaboradorRepository.save(colaborador);
    }

    /**
     * Busca un colaborador por su ID.
     * @param id ID del colaborador a buscar
     * @return Optional con el colaborador si existe, vacío si no
     */
    public Optional<Colaborador> obtenerPorId(int id){
        return colaboradorRepository.findById(id);
    }

    /**
     * Obtiene todos los colaboradores asociados a un proyecto específico.
     * @param idProyecto ID del proyecto
     * @return Lista de colaboradores del proyecto
     */
    public ArrayList<Colaborador> obtenerPorProyecto(int idProyecto){
        return colaboradorRepository.findByProyectoIdProyecto(idProyecto);
    }

    /**
     * Obtiene todos los colaboradores asociados a un sprint específico.
     * @param idSprint ID del sprint
     * @return Lista de colaboradores del sprint
     */
    public ArrayList<Colaborador> obtenerPorSprint(int idSprint){
        return colaboradorRepository.findBySprintIdSprint(idSprint);
    }
}
