package com.example.demo.entidades;

import jakarta.persistence.*;

@Entity
@Table(name = "COLABORADORES")
public class Colaborador {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_colaborador")
    private int idColaborador;
    
    @ManyToOne
    @JoinColumn(name = "id_proyecto_fk")
    private Proyecto proyecto;
    
    @ManyToOne
    @JoinColumn(name = "id_sprint_fk")
    private SprintProyecto sprint;
    
    @Column(name = "nombre_colaborador")
    private String nombreColaborador;
    
    @Column(name = "email_colaborador")
    private String emailColaborador;
    
    @Column(name = "rol_colaborador")
    private String rolColaborador;

    public int getIdColaborador() {
        return idColaborador;
    }

    public void setIdColaborador(int idColaborador) {
        this.idColaborador = idColaborador;
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

    public String getNombreColaborador() {
        return nombreColaborador;
    }

    public void setNombreColaborador(String nombreColaborador) {
        this.nombreColaborador = nombreColaborador;
    }

    public String getEmailColaborador() {
        return emailColaborador;
    }

    public void setEmailColaborador(String emailColaborador) {
        this.emailColaborador = emailColaborador;
    }

    public String getRolColaborador() {
        return rolColaborador;
    }

    public void setRolColaborador(String rolColaborador) {
        this.rolColaborador = rolColaborador;
    }

    public void registrar() {
        if (this.nombreColaborador == null || this.nombreColaborador.isEmpty()) {
            throw new IllegalArgumentException("El nombre es requerido");
        }
    }

    public void actualizar() {
        if (this.emailColaborador == null || this.emailColaborador.isEmpty()) {
            throw new IllegalArgumentException("El email es requerido");
        }
    }
}
