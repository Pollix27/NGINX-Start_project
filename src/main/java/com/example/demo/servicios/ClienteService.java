package com.example.demo.servicios;

import com.example.demo.entidades.Cliente;
import com.example.demo.interfaces.IRCliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

/**
 * Servicio para gestionar operaciones de negocio relacionadas con clientes.
 */
@Service
public class ClienteService {
    
    @Autowired
    private IRCliente repositorio;
    
    /**
     * Obtiene la lista de todos los clientes.
     * @return Lista de clientes
     */
    public List<Cliente> listarTodos() {
        return repositorio.findAll();
    }
    
    /**
     * Busca un cliente por su ID.
     * @param id Identificador del cliente
     * @return Optional con el cliente si existe
     */
    public Optional<Cliente> buscarPorId(int id) {
        return repositorio.findById(id);
    }
    
    /**
     * Guarda o actualiza un cliente.
     * @param cliente Cliente a guardar
     * @return Cliente guardado
     */
    public Cliente guardar(Cliente cliente) {
        return repositorio.save(cliente);
    }
    
    /**
     * Elimina un cliente por su ID.
     * @param id Identificador del cliente a eliminar
     */
    public void eliminar(int id) {
        repositorio.deleteById(id);
    }
}
