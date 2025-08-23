package com.gestion.academica.service;

import java.util.*;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gestion.academica.dto.EstudianteDTO;
import com.gestion.academica.entity.Estudiante;
import com.gestion.academica.repository.EstudianteRepository;

@Service
public class EstudianteService {
    @Autowired
    private EstudianteRepository estudianteRepository;

    // ===== CRUD existente (sin tocar) =====
    public Estudiante crear(Estudiante estudiante) {
        return estudianteRepository.save(estudiante);
    }

    public List<Estudiante> obtenerTodos() {
        return estudianteRepository.findAll();
    }

    public Optional<Estudiante> obtenerPorId(Long id) {
        return estudianteRepository.findById(id);
    }

    public Estudiante actualizar(Long id, Estudiante actualizado) {
        Estudiante estudiante = estudianteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Estudiante no encontrado"));

        estudiante.setNombre(actualizado.getNombre());
        estudiante.setApellidos(actualizado.getApellidos());
        estudiante.setDireccion(actualizado.getDireccion());
        estudiante.setTelefono(actualizado.getTelefono());

        return estudianteRepository.save(estudiante);
    }

    public void eliminar(Long id) {
        if (!estudianteRepository.existsById(id)) {
            throw new RuntimeException("Estudiante no encontrado");
        }
        estudianteRepository.deleteById(id);
    }

    // ===== NUEVO: DTOs + FILTROS para GET =====

    // Listado con filtros (?apellidos=...&nombre=...&telefono=...)
    public List<EstudianteDTO> listarDTO(String apellidos, String nombre, String telefono) {
        List<Estudiante> data;

        if (apellidos != null && !apellidos.isBlank()) {
            data = estudianteRepository.findByApellidosContainingIgnoreCase(apellidos);
        } else if (nombre != null && !nombre.isBlank()) {
            data = estudianteRepository.findByNombreContainingIgnoreCase(nombre);
        } else if (telefono != null && !telefono.isBlank()) {
            data = estudianteRepository.findByTelefonoContaining(telefono);
        } else {
            data = estudianteRepository.findAll();
        }

        return data.stream().map(this::toDTO).collect(Collectors.toList());
    }

    public EstudianteDTO obtenerDTO(Long id) {
        Estudiante e = estudianteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Estudiante " + id + " no encontrado"));
        return toDTO(e);
    }

    private EstudianteDTO toDTO(Estudiante e) {
        String nombreCompleto = ((e.getNombre() == null ? "" : e.getNombre()) + " " +
                                 (e.getApellidos() == null ? "" : e.getApellidos())).trim();
        // No exponemos dirección
        return new EstudianteDTO(e.getIdEstudiante(), nombreCompleto, e.getTelefono());
    }
}
