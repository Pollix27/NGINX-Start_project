package com.example.demo.entidades;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "RECURSOS")
public class Recurso {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_recurso")
    private int idRecurso;
    
    @ManyToOne
    @JoinColumn(name = "id_proyecto_fk")
    private Proyecto proyecto;
    
    @Column(name = "cantidad_colaboradores")
    private int cantidadColaboradores;
    
    @Column(name = "horas_estimadas")
    private int horasEstimadas;
    
    @Column(name = "horas_adicionales")
    private int horasAdicionales;
    
    @Column(name = "tarifa_hora")
    private BigDecimal tarifaHora;

    public int getIdRecurso() {
        return idRecurso;
    }

    public void setIdRecurso(int idRecurso) {
        this.idRecurso = idRecurso;
    }

    public Proyecto getProyecto() {
        return proyecto;
    }

    public void setProyecto(Proyecto proyecto) {
        this.proyecto = proyecto;
    }

    public int getCantidadColaboradores() {
        return cantidadColaboradores;
    }

    public void setCantidadColaboradores(int cantidadColaboradores) {
        this.cantidadColaboradores = cantidadColaboradores;
    }

    public int getHorasEstimadas() {
        return horasEstimadas;
    }

    public void setHorasEstimadas(int horasEstimadas) {
        this.horasEstimadas = horasEstimadas;
    }

    public int getHorasAdicionales() {
        return horasAdicionales;
    }

    public void setHorasAdicionales(int horasAdicionales) {
        this.horasAdicionales = horasAdicionales;
    }

    public BigDecimal getTarifaHora() {
        return tarifaHora;
    }

    public void setTarifaHora(BigDecimal tarifaHora) {
        this.tarifaHora = tarifaHora;
    }

    public void estimar() {
        if (this.horasEstimadas <= 0) {
            throw new IllegalArgumentException("Las horas estimadas deben ser mayores a 0");
        }
    }

    public BigDecimal calcularCosto() {
        int totalHoras = this.horasEstimadas + this.horasAdicionales;
        return this.tarifaHora.multiply(BigDecimal.valueOf(totalHoras));
    }
}
