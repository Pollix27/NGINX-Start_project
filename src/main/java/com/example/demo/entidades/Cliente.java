package com.example.demo.entidades;

import jakarta.persistence.*;

@Entity
@Table(name = "cliente")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private int id;

    private String nombre;
    private String email;
    private String empresa;
    private String rfc;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmpresa() {
        return empresa;
    }

    public String getRfc() {
        return rfc;
    }

    public void setRfc(String rfc) {
        if (rfc != null && !rfc.matches("^[A-Z0-9]{12,13}$")) {
            throw new IllegalArgumentException("RFC debe contener 12-13 caracteres alfanuméricos");
        }
        this.rfc = rfc;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public void registrar() {
        if (this.nombre == null || this.nombre.isEmpty()) {
            throw new IllegalArgumentException("El nombre es requerido");
        }
        if (this.email == null || this.email.isEmpty()) {
            throw new IllegalArgumentException("El email es requerido");
        }
    }

    public void actualizar() {
        if (this.id <= 0) {
            throw new IllegalStateException("No se puede actualizar un cliente sin ID");
        }
    }

}