package com.example.demo.entidades;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "proyecto")
public class Proyecto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private int id;

    private String codigo;
    private String nombre;
    private String descripcion;
    private String alcance;
    
    @Temporal(TemporalType.DATE)
    private Date fechaInicio;
    
    @Temporal(TemporalType.DATE)
    private Date fechaFin;
    
    private String estado;
    
    @ManyToOne
    @JoinColumn(name = "id_cliente")
    private Cliente cliente;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getAlcance() {
        return alcance;
    }

    public void setAlcance(String alcance) {
        this.alcance = alcance;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public void crear() {
        if (this.nombre == null || this.nombre.isEmpty()) {
            throw new IllegalArgumentException("El nombre es requerido");
        }
        if (this.codigo == null || this.codigo.isEmpty()) {
            throw new IllegalArgumentException("El código es requerido");
        }
    }

    public void actualizarEstado() {
        if (this.id <= 0) {
            throw new IllegalStateException("No se puede actualizar un proyecto sin ID");
        }
    }

    public long calcularDuracion() {
        if (fechaInicio != null && fechaFin != null) {
            return (fechaFin.getTime() - fechaInicio.getTime()) / (1000 * 60 * 60 * 24);
        }
        return 0;
    }
}
