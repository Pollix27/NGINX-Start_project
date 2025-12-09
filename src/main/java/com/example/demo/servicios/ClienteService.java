package com.example.demo.servicios;

import com.example.demo.entidades.Cliente;

import com.example.demo.interfaces.IRCliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

/**
 * Servicio para la lógica de negocio de Cliente.
 * Gestiona las operaciones relacionadas con los clientes.
 * 
 * @author NGINX
 * @version 1.0
 */
@Service
public class ClienteService {
    /** Repositorio de clientes inyectado automáticamente */
    @Autowired
    IRCliente IRCliente;

    /**
     * Obtiene todos los clientes registrados.
     * @return Lista de todos los clientes
     */
    public ArrayList<Cliente> obtenerCliente(){
        return (ArrayList<Cliente>) IRCliente.findAll();
    }

    /**
     * Guarda o actualiza un cliente en la base de datos.
     * @param cliente Cliente a guardar
     * @return Cliente guardado con su ID generado
     */
    public Cliente guardarCliente(Cliente cliente){
        return IRCliente.save(cliente);
    }

    /**
     * Busca un cliente por su ID.
     * @param id ID del cliente a buscar
     * @return Optional con el cliente si existe, vacío si no
     */
    public Optional<Cliente> obtenerPorId(int id){
        return IRCliente.findById(id);
    }


}