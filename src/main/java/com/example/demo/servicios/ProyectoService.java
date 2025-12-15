package com.example.demo.servicios;

import com.example.demo.entidades.Cliente;
import com.example.demo.entidades.Proyecto;
import com.example.demo.interfaces.IRCliente;
import com.example.demo.interfaces.IRProyecto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

/**
 * Servicio para gestionar operaciones de negocio relacionadas con proyectos.
 */
@Service
public class ProyectoService {
    
    @Autowired
    private IRProyecto repositorio;
    
    @Autowired
    private IRCliente clienteRepositorio;
    
    /**
     * Obtiene la lista de todos los proyectos.
     * @return Lista de proyectos
     */
    public List<Proyecto> listarTodos() {
        return repositorio.findAll();
    }
    
    /**
     * Busca un proyecto por su ID.
     * @param id Identificador del proyecto
     * @return Optional con el proyecto si existe
     */
    public Optional<Proyecto> buscarPorId(int id) {
        return repositorio.findById(id);
    }
    
    /**
     * Guarda o actualiza un proyecto.
     * Valida y asigna el cliente correspondiente.
     * @param proyecto Proyecto a guardar
     * @return Proyecto guardado
     */
    public Proyecto guardar(Proyecto proyecto) {
        if (proyecto.getCliente() != null && proyecto.getCliente().getIdCliente() > 0) {
            Cliente cliente = clienteRepositorio.findById(proyecto.getCliente().getIdCliente())
                    .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));
            proyecto.setCliente(cliente);
        }
        return repositorio.save(proyecto);
    }
    
    /**
     * Elimina un proyecto por su ID.
     * @param id Identificador del proyecto a eliminar
     */
    public void eliminar(int id) {
        repositorio.deleteById(id);
    }
}
