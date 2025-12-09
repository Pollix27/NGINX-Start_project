package com.example.demo.entidades;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "SPRINTS_DE_PROYECTOS")
public class SprintsDeProyecto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_sprint")
    private int idSprint;

    @Column(name = "nombre_sprint", nullable = false, length = 50)
    private String nombreSprint;

    @Column(name = "fecha_inicio_sprint", nullable = false)
    private Date fechaInicioSprint;

    @Column(name = "fecha_fin_sprint", nullable = false)
    private Date fechaFinSprint;

    @Column(name = "estado_sprint", nullable = false, length = 50)
    private String estadoSprint;

    @ManyToOne
    @JoinColumn(name = "id_proyecto", nullable = false)
    private Proyecto proyecto;

    public int getIdSprint() {
        return idSprint;
    }

    public void setIdSprint(int idSprint) {
        this.idSprint = idSprint;
    }

    public String getNombreSprint() {
        return nombreSprint;
    }

    public void setNombreSprint(String nombreSprint) {
        this.nombreSprint = nombreSprint;
    }

    public Date getFechaInicioSprint() {
        return fechaInicioSprint;
    }

    public void setFechaInicioSprint(Date fechaInicioSprint) {
        this.fechaInicioSprint = fechaInicioSprint;
    }

    public Date getFechaFinSprint() {
        return fechaFinSprint;
    }

    public void setFechaFinSprint(Date fechaFinSprint) {
        this.fechaFinSprint = fechaFinSprint;
    }

    public String getEstadoSprint() {
        return estadoSprint;
    }

    public void setEstadoSprint(String estadoSprint) {
        this.estadoSprint = estadoSprint;
    }

    public Proyecto getProyecto() {
        return proyecto;
    }

    public void setProyecto(Proyecto proyecto) {
        this.proyecto = proyecto;
    }

}
