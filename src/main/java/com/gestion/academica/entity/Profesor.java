package com.gestion.academica.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "profesor")
public class Profesor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProfesor;

    private String nombre;
    private String apellidos;
    private String direccion;
    private String telefono;



    // Default constructor
    public Profesor() {
    }


    // Constructor parametrizado
    public Profesor(Long idProfesor, String nombre, String apellidos, String direccion, String telefono) {
        this.idProfesor = idProfesor;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.direccion = direccion;
        this.telefono = telefono;
    }



    // Getters and Setters
    public Long getidProfesor() {
        return idProfesor;
    }

    public void setidProfesor(Long idProfesor) {
        this.idProfesor = idProfesor;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

}
