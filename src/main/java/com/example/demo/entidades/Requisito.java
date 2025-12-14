package com.example.demo.entidades;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;

import java.util.List;

@Data
@Entity
@Table(name = "REQUISITOS")
public class Requisito {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_requisitos")
    private int idRequisitos;
    
    @ManyToOne
    @JoinColumn(name = "id_proyecto_fk", nullable = false)
    @JsonBackReference("proyecto-requisitos")
    private Proyecto proyecto;
    
    @ManyToOne
    @JoinColumn(name = "id_sprint_fk")
    @JsonBackReference("sprint-requisitos")
    private Sprint sprint;
    
    @Column(name = "descripcion_requisitos", columnDefinition = "TEXT")
    private String descripcionRequisitos;
    
    @Column(name = "prioridad_requisitos", length = 50)
    private String prioridadRequisitos;
    
    @OneToMany(mappedBy = "requisito", cascade = CascadeType.ALL)
    @JsonManagedReference("requisito-presupuestos")
    private List<Presupuesto> presupuestos;

    public Requisito() {}

}
