package com.example.demo.entidades;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "presupuesto_de_proyecto")
public class PresupuestosDeProyecto {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_presupuesto")
    private int idPresupuesto;
    
    @ManyToOne
    @JoinColumn(name = "id_proyecto_fk")
    private Proyecto proyecto;
    
    @Column(name = "total_presupuesto")
    private BigDecimal totalPresupuesto;
    
    @Temporal(TemporalType.DATE)
    @Column(name = "fecha_elaboracion")
    private Date fechaElaboracion;
    
    @Column(name = "estado_presupuesto")
    private String estadoPresupuesto;

    public int getIdPresupuesto() {
        return idPresupuesto;
    }

    public void setIdPresupuesto(int idPresupuesto) {
        this.idPresupuesto = idPresupuesto;
    }

    public Proyecto getProyecto() {
        return proyecto;
    }

    public void setProyecto(Proyecto proyecto) {
        this.proyecto = proyecto;
    }

    public BigDecimal getTotalPresupuesto() {
        return totalPresupuesto;
    }

    public void setTotalPresupuesto(BigDecimal totalPresupuesto) {
        this.totalPresupuesto = totalPresupuesto;
    }

    public Date getFechaElaboracion() {
        return fechaElaboracion;
    }

    public void setFechaElaboracion(Date fechaElaboracion) {
        this.fechaElaboracion = fechaElaboracion;
    }

    public String getEstadoPresupuesto() {
        return estadoPresupuesto;
    }

    public void setEstadoPresupuesto(String estadoPresupuesto) {
        this.estadoPresupuesto = estadoPresupuesto;
    }

    public void calcular() {
        if (this.totalPresupuesto == null || this.totalPresupuesto.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("El total debe ser mayor a 0");
        }
    }

    public void aprobar() {
        this.estadoPresupuesto = "Aprobado";
    }
}
