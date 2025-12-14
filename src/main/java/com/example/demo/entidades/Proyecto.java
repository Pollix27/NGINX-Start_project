package com.example.demo.entidades;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

@Data
@Getter
@Entity
@Table(name = "PROYECTOS")
public class Proyecto {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_proyecto")
    private int idProyecto;
    
    @ManyToOne
    @JoinColumn(name = "id_cliente_fk", nullable = false)
    private Cliente cliente;
    
    @Column(name = "nombre_proyecto", nullable = false, length = 200)
    private String nombreProyecto;
    
    @Column(name = "descripcion_proyecto", columnDefinition = "TEXT")
    private String descripcionProyecto;
    
    @Column(name = "complejidad_proyecto", length = 50)
    private String complejidadProyecto;
    
    @Column(name = "fecha_inicio_proyecto")
    private LocalDate fechaInicioProyecto;
    
    @Column(name = "fecha_fin_proyecto")
    private LocalDate fechaFinProyecto;
    
    @Column(name = "estado_proyecto", length = 50)
    private String estadoProyecto;
    
    @OneToMany(mappedBy = "proyecto", cascade = CascadeType.ALL)
    private List<Requisito> requisitos;
    
    @OneToMany(mappedBy = "proyecto", cascade = CascadeType.ALL)
    private List<Sprint> sprints;
    
    @OneToMany(mappedBy = "proyecto", cascade = CascadeType.ALL)
    private List<Colaborador> colaboradores;
    
    @OneToMany(mappedBy = "proyecto", cascade = CascadeType.ALL)
    private List<Presupuesto> presupuestos;

    public Proyecto() {}

    @Override
    public String toString() {
        return "Proyecto{" +
                "idProyecto=" + idProyecto +
                ", cliente=" + cliente +
                ", nombreProyecto='" + nombreProyecto + '\'' +
                ", descripcionProyecto='" + descripcionProyecto + '\'' +
                ", complejidadProyecto='" + complejidadProyecto + '\'' +
                ", fechaInicioProyecto=" + fechaInicioProyecto +
                ", fechaFinProyecto=" + fechaFinProyecto +
                ", estadoProyecto='" + estadoProyecto + '\'' +
                ", requisitos=" + requisitos +
                ", sprints=" + sprints +
                ", colaboradores=" + colaboradores +
                ", presupuestos=" + presupuestos +
                '}';
    }
}
