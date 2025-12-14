package com.example.demo.servicios;

import com.example.demo.entidades.Colaborador;
import com.example.demo.entidades.Proyecto;
import com.example.demo.entidades.Sprint;
import com.example.demo.interfaces.IRColaborador;
import com.example.demo.interfaces.IRProyecto;
import com.example.demo.interfaces.IRSprint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ColaboradorService {
    
    @Autowired
    private IRColaborador repositorio;
    
    @Autowired
    private IRProyecto proyectoRepositorio;
    
    @Autowired
    private IRSprint sprintRepositorio;
    
    public List<Colaborador> listarTodos() {
        return repositorio.findAll();
    }
    
    public Optional<Colaborador> buscarPorId(int id) {
        return repositorio.findById(id);
    }
    
    public Colaborador guardar(Colaborador colaborador) {
        if (colaborador.getProyecto() != null && colaborador.getProyecto().getIdProyecto() > 0) {
            Proyecto proyecto = proyectoRepositorio.findById(colaborador.getProyecto().getIdProyecto())
                    .orElseThrow(() -> new RuntimeException("Proyecto no encontrado"));
            colaborador.setProyecto(proyecto);
        }
        if (colaborador.getSprint() != null && colaborador.getSprint().getIdSprint() > 0) {
            Sprint sprint = sprintRepositorio.findById(colaborador.getSprint().getIdSprint())
                    .orElseThrow(() -> new RuntimeException("Sprint no encontrado"));
            colaborador.setSprint(sprint);
        } else {
            colaborador.setSprint(null);
        }
        return repositorio.save(colaborador);
    }
    
    public void eliminar(int id) {
        repositorio.deleteById(id);
    }
}
