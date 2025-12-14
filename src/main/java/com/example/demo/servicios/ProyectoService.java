package com.example.demo.servicios;

import com.example.demo.entidades.Cliente;
import com.example.demo.entidades.Proyecto;
import com.example.demo.interfaces.IRCliente;
import com.example.demo.interfaces.IRProyecto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ProyectoService {
    
    @Autowired
    private IRProyecto repositorio;
    
    @Autowired
    private IRCliente clienteRepositorio;
    
    public List<Proyecto> listarTodos() {
        return repositorio.findAll();
    }
    
    public Optional<Proyecto> buscarPorId(int id) {
        return repositorio.findById(id);
    }
    
    public Proyecto guardar(Proyecto proyecto) {
        if (proyecto.getCliente() != null && proyecto.getCliente().getIdCliente() > 0) {
            Cliente cliente = clienteRepositorio.findById(proyecto.getCliente().getIdCliente())
                    .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));
            proyecto.setCliente(cliente);
        }
        return repositorio.save(proyecto);
    }
    
    public void eliminar(int id) {
        repositorio.deleteById(id);
    }
}
