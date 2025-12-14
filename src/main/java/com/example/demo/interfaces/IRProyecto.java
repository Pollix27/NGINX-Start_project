package com.example.demo.interfaces;

import com.example.demo.entidades.Proyecto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRProyecto extends JpaRepository<Proyecto, Integer> {
}
