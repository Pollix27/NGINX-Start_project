package com.example.demo.entidades;

import jakarta.persistence.*;

@Entity
@Table(name = "REQUERIMIENTOS_DE_PROYECTO ")
public class RequerimientoProyecto {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_requerimiento")
    private int idRequerimiento;
    
    @ManyToOne
    @JoinColumn(name = "id_proyecto_fk")
    private Proyecto proyecto;
    
    @ManyToOne
    @JoinColumn(name = "id_sprint_fk")
    private SprintProyecto sprint;
    
    @Column(name = "descripcion_requerimiento")
    private String descripcionRequerimiento;
    
    @Column(name = "tipo_requerimiento")
    private String tipoRequerimiento;
    
    @Column(name = "prioridad_requerimiento")
    private String prioridadRequerimiento;
    
    @Column(name = "horas_estimadas")
    private int horasEstimadas;

    public int getIdRequerimiento() {
        return idRequerimiento;
    }

    public void setIdRequerimiento(int idRequerimiento) {
        this.idRequerimiento = idRequerimiento;
    }

    public Proyecto getProyecto() {
        return proyecto;
    }

    public void setProyecto(Proyecto proyecto) {
        this.proyecto = proyecto;
    }

    public SprintProyecto getSprint() {
        return sprint;
    }

    public void setSprint(SprintProyecto sprint) {
        this.sprint = sprint;
    }

    public String getDescripcionRequerimiento() {
        return descripcionRequerimiento;
    }

    public void setDescripcionRequerimiento(String descripcionRequerimiento) {
        this.descripcionRequerimiento = descripcionRequerimiento;
    }

    public String getTipoRequerimiento() {
        return tipoRequerimiento;
    }

    public void setTipoRequerimiento(String tipoRequerimiento) {
        this.tipoRequerimiento = tipoRequerimiento;
    }

    public String getPrioridadRequerimiento() {
        return prioridadRequerimiento;
    }

    public void setPrioridadRequerimiento(String prioridadRequerimiento) {
        this.prioridadRequerimiento = prioridadRequerimiento;
    }

    public int getHorasEstimadas() {
        return horasEstimadas;
    }

    public void setHorasEstimadas(int horasEstimadas) {
        this.horasEstimadas = horasEstimadas;
    }

    public void agregar() {
        if (this.descripcionRequerimiento == null || this.descripcionRequerimiento.isEmpty()) {
            throw new IllegalArgumentException("La descripción es requerida");
        }
    }

    public void estimar() {
        if (this.horasEstimadas <= 0) {
            throw new IllegalArgumentException("Las horas estimadas deben ser mayores a 0");
        }
    }
}
