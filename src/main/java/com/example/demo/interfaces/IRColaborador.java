package com.example.demo.interfaces;

import com.example.demo.entidades.Colaborador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRColaborador extends JpaRepository<Colaborador, Integer> {
}
