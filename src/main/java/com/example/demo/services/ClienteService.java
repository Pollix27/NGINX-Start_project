package com.example.demo.services;

import com.example.demo.models.ClienteModel;

import com.example.demo.repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class ClienteService {
    @Autowired
    ClienteRepository clienteRepository;

    public ArrayList<ClienteModel> obtenerCliente(){
        return (ArrayList<ClienteModel>) clienteRepository.findAll();
    }

    public ClienteModel guardarCliente(ClienteModel cliente){
        return clienteRepository.save(cliente);
    }

    public Optional<ClienteModel> obtenerPorId(int id){
        return clienteRepository.findById(id);
    }


    public ArrayList<ClienteModel> obtenerPorPrioridad(Integer prioridad){
        return clienteRepository.findByPrioridad(prioridad);
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