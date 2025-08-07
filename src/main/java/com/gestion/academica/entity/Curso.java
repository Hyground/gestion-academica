package com.gestion.academica.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "curso")
public class Curso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCurso;

    private String nombre;
    private String descripcion;

    @ManyToOne
    @JoinColumn(name = "id_profesor")
    private Profesor profesor;

    // Default constructor
    public Curso() {
    }

    // Constructor parametrizado
    public Curso(Long idCurso, String nombre, String descripcion, Profesor profesor) {
        this.idCurso = idCurso;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.profesor = profesor;
    }
    public Long getIdCurso() {
        return idCurso;
    }
    public void setIdCurso(Long idCurso) {
        this.idCurso = idCurso;
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
    public Profesor getProfesor() {
        return profesor;
    }
    public void setProfesor(Profesor profesor) {
        this.profesor = profesor;
    }
    
}
