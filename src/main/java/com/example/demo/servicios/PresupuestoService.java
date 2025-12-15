package com.example.demo.servicios;

import com.example.demo.entidades.*;
import com.example.demo.interfaces.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

/**
 * Servicio para gestionar operaciones de negocio relacionadas con presupuestos.
 */
@Service
public class PresupuestoService {
    
    @Autowired
    private IRPresupuesto repositorio;
    
    @Autowired
    private IRProyecto proyectoRepositorio;
    
    @Autowired
    private IRRequisito requisitoRepositorio;
    
    @Autowired
    private IRColaborador colaboradorRepositorio;
    
    /**
     * Obtiene la lista de todos los presupuestos.
     * @return Lista de presupuestos
     */
    public List<Presupuesto> listarTodos() {
        return repositorio.findAll();
    }
    
    /**
     * Busca un presupuesto por su ID.
     * @param id Identificador del presupuesto
     * @return Optional con el presupuesto si existe
     */
    public Optional<Presupuesto> buscarPorId(int id) {
        return repositorio.findById(id);
    }
    
    /**
     * Guarda o actualiza un presupuesto.
     * Valida y asigna el proyecto, requisito y colaborador correspondientes.
     * @param presupuesto Presupuesto a guardar
     * @return Presupuesto guardado
     */
    public Presupuesto guardar(Presupuesto presupuesto) {
        if (presupuesto == null) {
            throw new IllegalArgumentException("El presupuesto no puede ser nulo");
        }
        if (presupuesto.getProyecto() == null || presupuesto.getProyecto().getIdProyecto() <= 0) {
            throw new IllegalArgumentException("El presupuesto debe tener un proyecto válido");
        }
        Proyecto proyecto = proyectoRepositorio.findById(presupuesto.getProyecto().getIdProyecto())
                .orElseThrow(() -> new RuntimeException("Proyecto no encontrado con ID: " + presupuesto.getProyecto().getIdProyecto()));
        presupuesto.setProyecto(proyecto);
        
        if (presupuesto.getRequisito() != null && presupuesto.getRequisito().getIdRequisitos() > 0) {
            Requisito requisito = requisitoRepositorio.findById(presupuesto.getRequisito().getIdRequisitos())
                    .orElseThrow(() -> new RuntimeException("Requisito no encontrado"));
            presupuesto.setRequisito(requisito);
        } else {
            presupuesto.setRequisito(null);
        }
        
        if (presupuesto.getColaborador() != null && presupuesto.getColaborador().getIdColaborador() > 0) {
            Colaborador colaborador = colaboradorRepositorio.findById(presupuesto.getColaborador().getIdColaborador())
                    .orElseThrow(() -> new RuntimeException("Colaborador no encontrado"));
            presupuesto.setColaborador(colaborador);
        } else {
            presupuesto.setColaborador(null);
        }
        
        return repositorio.save(presupuesto);
    }
    
    /**
     * Elimina un presupuesto por su ID.
     * @param id Identificador del presupuesto a eliminar
     */
    public void eliminar(int id) {
        repositorio.deleteById(id);
    }
}
