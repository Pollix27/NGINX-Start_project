package com.example.demo.servicios;

import com.example.demo.entidades.Cliente;

import com.example.demo.repositorios.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class ClienteService {
    @Autowired
    ClienteRepository clienteRepository;

    public ArrayList<Cliente> obtenerCliente(){
        return (ArrayList<Cliente>) clienteRepository.findAll();
    }

    public Cliente guardarCliente(Cliente cliente){
        return clienteRepository.save(cliente);
    }

    public Optional<Cliente> obtenerPorId(int id){
        return clienteRepository.findById(id);
    }

    public boolean eliminarCliente(int id) {
        try{
            clienteRepository.deleteById(id);
            return true;
        }catch (Exception err){
            return false;
        }
    }

}