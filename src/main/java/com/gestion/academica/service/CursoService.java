package com.gestion.academica.service;

import java.util.*;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gestion.academica.dto.CursoDTO;
import com.gestion.academica.entity.Curso;
import com.gestion.academica.repository.CursoRepository;

@Service
public class CursoService {
    @Autowired
    private CursoRepository cursoRepository;

    // ===== CRUD existente (sin tocar) =====
    public Curso crear(Curso curso) {
        return cursoRepository.save(curso);
    }

    public List<Curso> obtenerTodos() {
        return cursoRepository.findAll();
    }

    public Optional<Curso> obtenerPorId(Long id) {
        return cursoRepository.findById(id);
    }

    public Curso actualizar(Long id, Curso actualizado) {
        Curso curso = cursoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Curso no encontrado"));

        curso.setNombre(actualizado.getNombre());
        curso.setDescripcion(actualizado.getDescripcion());
        curso.setProfesor(actualizado.getProfesor());

        return cursoRepository.save(curso);
    }

    public void eliminar(Long id) {
        if (!cursoRepository.existsById(id)) {
            throw new RuntimeException("Curso no encontrado");
        }
        cursoRepository.deleteById(id);
    }

    // ===== NUEVO: DTOs + FILTROS para GET =====

    // Listado con filtros (?nombre=...&descripcion=...&profesorId=...)
    public List<CursoDTO> listarDTO(String nombre, String descripcion, Long profesorId) {
        List<Curso> data;

        if (profesorId != null) {
            data = cursoRepository.findByProfesor_IdProfesor(profesorId); // ver repo más abajo
        } else if (nombre != null && !nombre.isBlank()) {
            data = cursoRepository.findByNombreContainingIgnoreCase(nombre);
        } else if (descripcion != null && !descripcion.isBlank()) {
            data = cursoRepository.findByDescripcionContainingIgnoreCase(descripcion);
        } else {
            data = cursoRepository.findAll();
        }

        return data.stream().map(this::toDTO).collect(Collectors.toList());
    }

    public CursoDTO obtenerDTO(Long id) {
        Curso c = cursoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Curso " + id + " no encontrado"));
        return toDTO(c);
    }

    private CursoDTO toDTO(Curso c) {
        String profNombre = (c.getProfesor() != null)
                ? (c.getProfesor().getNombre() + " " + c.getProfesor().getApellidos()).trim()
                : null;
        return new CursoDTO(c.getIdCurso(), c.getNombre(), c.getDescripcion(), profNombre);
    }
}
