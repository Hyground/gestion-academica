package com.gestion.academica.entity;

import java.time.LocalDate;

import jakarta.persistence.*;

@Entity
@Table(name = "inscripcion")
public class Inscripcion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idInscripcion;

    private LocalDate fecha;

    @ManyToOne
    @JoinColumn(name = "id_curso")
    private Curso curso;

    @ManyToOne
    @JoinColumn(name = "id_estudiante")
    private Estudiante estudiante;

    // Default constructor
    public Inscripcion() {
    }
    // Constructor parametrizado
    public Inscripcion(Long idInscripcion, LocalDate fecha, Curso curso, Estudiante estudiante) {
        this.idInscripcion = idInscripcion;
        this.fecha = fecha;
        this.curso = curso;
        this.estudiante = estudiante;
    }
    // Getters and Setters
    public Long getIdInscripcion() {
        return idInscripcion;
    }
    public void setIdInscripcion(Long idInscripcion) {
        this.idInscripcion = idInscripcion;
    }
    public LocalDate getFecha() {
        return fecha;
    }
    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }
    public Curso getCurso() {
        return curso;
    }
    public void setCurso(Curso curso) {
        this.curso = curso;
    }
    public Estudiante getEstudiante() {
        return estudiante;
    }
    public void setEstudiante(Estudiante estudiante) {
        this.estudiante = estudiante;

    }
}
