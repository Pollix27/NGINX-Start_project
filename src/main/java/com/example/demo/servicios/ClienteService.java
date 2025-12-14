package com.example.demo.servicios;

import com.example.demo.entidades.Cliente;
import com.example.demo.interfaces.IRCliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {
    
    @Autowired
    private IRCliente repositorio;
    
    public List<Cliente> listarTodos() {
        return repositorio.findAll();
    }
    
    public Optional<Cliente> buscarPorId(int id) {
        return repositorio.findById(id);
    }
    
    public Cliente guardar(Cliente cliente) {
        return repositorio.save(cliente);
    }
    
    public void eliminar(int id) {
        repositorio.deleteById(id);
    }
}
