package com.example.demo.entidades;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.List;

@Data
@Getter
@Entity
@Table(name = "COLABORADORES")
public class Colaborador {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_colaborador")
    private int idColaborador;
    
    @ManyToOne
    @JoinColumn(name = "id_proyecto_fk", nullable = false)
    private Proyecto proyecto;
    
    @ManyToOne
    @JoinColumn(name = "id_sprint_fk")
    private Sprint sprint;
    
    @Column(name = "nombre_colaborador", length = 100)
    private String nombreColaborador;
    
    @Column(name = "email_colaborador", length = 100)
    private String emailColaborador;
    
    @Column(name = "rol_colaborador", length = 50)
    private String rolColaborador;
    
    @Column(name = "tarifa_hora_programador", precision = 10, scale = 2)
    private BigDecimal tarifaHoraProgramador;
    
    @OneToMany(mappedBy = "colaborador", cascade = CascadeType.ALL)
    private List<Presupuesto> presupuestos;

    public Colaborador() {}

}
