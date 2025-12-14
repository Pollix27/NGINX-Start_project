package com.example.demo.servicios;

import com.example.demo.entidades.*;
import com.example.demo.interfaces.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

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
    
    public List<Presupuesto> listarTodos() {
        return repositorio.findAll();
    }
    
    public Optional<Presupuesto> buscarPorId(int id) {
        return repositorio.findById(id);
    }
    
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
    
    public void eliminar(int id) {
        repositorio.deleteById(id);
    }
}
