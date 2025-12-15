package com.example.demo.servicios;

import com.example.demo.entidades.Proyecto;
import com.example.demo.entidades.Requisito;
import com.example.demo.entidades.Sprint;
import com.example.demo.interfaces.IRProyecto;
import com.example.demo.interfaces.IRRequisito;
import com.example.demo.interfaces.IRSprint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

/**
 * Servicio para gestionar operaciones de negocio relacionadas con requisitos.
 */
@Service
public class RequisitoService {
    
    @Autowired
    private IRRequisito repositorio;
    
    @Autowired
    private IRProyecto proyectoRepositorio;
    
    @Autowired
    private IRSprint sprintRepositorio;
    
    /**
     * Obtiene la lista de todos los requisitos.
     * @return Lista de requisitos
     */
    public List<Requisito> listarTodos() {
        return repositorio.findAll();
    }
    
    /**
     * Busca un requisito por su ID.
     * @param id Identificador del requisito
     * @return Optional con el requisito si existe
     */
    public Optional<Requisito> buscarPorId(int id) {
        return repositorio.findById(id);
    }
    
    /**
     * Guarda o actualiza un requisito.
     * Valida y asigna el proyecto y sprint correspondientes.
     * @param requisito Requisito a guardar
     * @return Requisito guardado
     */
    public Requisito guardar(Requisito requisito) {
        if (requisito == null) {
            throw new IllegalArgumentException("El requisito no puede ser nulo");
        }
        if (requisito.getProyecto() == null || requisito.getProyecto().getIdProyecto() <= 0) {
            throw new IllegalArgumentException("El requisito debe tener un proyecto válido");
        }
        Proyecto proyecto = proyectoRepositorio.findById(requisito.getProyecto().getIdProyecto())
                .orElseThrow(() -> new RuntimeException("Proyecto no encontrado con ID: " + requisito.getProyecto().getIdProyecto()));
        requisito.setProyecto(proyecto);
        
        if (requisito.getSprint() != null && requisito.getSprint().getIdSprint() > 0) {
            Sprint sprint = sprintRepositorio.findById(requisito.getSprint().getIdSprint())
                    .orElseThrow(() -> new RuntimeException("Sprint no encontrado con ID: " + requisito.getSprint().getIdSprint()));
            requisito.setSprint(sprint);
        } else {
            requisito.setSprint(null);
        }
        return repositorio.save(requisito);
    }
    
    /**
     * Elimina un requisito por su ID.
     * @param id Identificador del requisito a eliminar
     */
    public void eliminar(int id) {
        if (!repositorio.existsById(id)) {
            throw new RuntimeException("Requisito no encontrado con ID: " + id);
        }
        repositorio.deleteById(id);
    }
}
