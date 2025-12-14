package com.example.demo.entidades;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

@Data
@Getter
@Entity
@Table(name = "SPRINTS")
public class Sprint {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_sprint")
    private int idSprint;
    
    @ManyToOne
    @JoinColumn(name = "id_proyecto_fk", nullable = false)
    @JsonBackReference("proyecto-sprints")
    private Proyecto proyecto;
    
    @Column(name = "nombre_sprint", length = 100)
    private String nombreSprint;
    
    @Column(name = "estado_sprint", length = 50)
    private String estadoSprint;
    
    @Column(name = "fecha_inicio_sprint")
    private LocalDate fechaInicioSprint;
    
    @Column(name = "fecha_fin_sprint")
    private LocalDate fechaFinSprint;
    
    @OneToMany(mappedBy = "sprint", cascade = CascadeType.ALL)
    @JsonManagedReference("sprint-requisitos")
    private List<Requisito> requisitos;
    
    @OneToMany(mappedBy = "sprint", cascade = CascadeType.ALL)
    @JsonManagedReference("sprint-colaboradores")
    private List<Colaborador> colaboradores;

    public Sprint() {}

}
