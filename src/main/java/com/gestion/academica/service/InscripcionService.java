package com.gestion.academica.service;

import java.util.*;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gestion.academica.dto.InscripcionDTO;
import com.gestion.academica.entity.Inscripcion;
import com.gestion.academica.repository.InscripcionRepository;

@Service
public class InscripcionService {
    @Autowired
    private InscripcionRepository inscripcionRepository;

    // ===== CRUD existente (sin tocar) =====
    public Inscripcion crear(Inscripcion inscripcion) {
        return inscripcionRepository.save(inscripcion);
    }

    public List<Inscripcion> obtenerTodos() {
        return inscripcionRepository.findAll();
    }

    public Optional<Inscripcion> obtenerPorId(Long id) {
        return inscripcionRepository.findById(id);
    }

    public Inscripcion actualizar(Long id, Inscripcion actualizada) {
        Inscripcion inscripcion = inscripcionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Inscripción no encontrada"));

        inscripcion.setFecha(actualizada.getFecha());
        inscripcion.setCurso(actualizada.getCurso());
        inscripcion.setEstudiante(actualizada.getEstudiante());

        return inscripcionRepository.save(inscripcion);
    }

    public void eliminar(Long id) {
        if (!inscripcionRepository.existsById(id)) {
            throw new RuntimeException("Inscripción no encontrada");
        }
        inscripcionRepository.deleteById(id);
    }

    // ===== NUEVO: DTOs + FILTROS para GET =====

    // Listado con filtros (?cursoId=...&estudianteId=...)
    public List<InscripcionDTO> listarDTO(Long cursoId, Long estudianteId) {
        List<Inscripcion> data;

        if (cursoId != null) {
            data = inscripcionRepository.findByCurso_IdCurso(cursoId);
        } else if (estudianteId != null) {
            data = inscripcionRepository.findByEstudiante_IdEstudiante(estudianteId);
        } else {
            data = inscripcionRepository.findAll();
        }

        return data.stream().map(this::toDTO).collect(Collectors.toList());
    }

    public InscripcionDTO obtenerDTO(Long id) {
        Inscripcion i = inscripcionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Inscripción " + id + " no encontrada"));
        return toDTO(i);
    }

    private InscripcionDTO toDTO(Inscripcion i) {
        var e = i.getEstudiante();
        var c = i.getCurso();
        String estNombre = (e == null) ? null : (e.getNombre() + " " + e.getApellidos()).trim();
        String cursoNombre = (c == null) ? null : c.getNombre();
        Long estudianteId = (e == null) ? null : e.getIdEstudiante();
        Long cursoId = (c == null) ? null : c.getIdCurso();

        return new InscripcionDTO(i.getIdInscripcion(), i.getFecha(), estudianteId, estNombre, cursoId, cursoNombre);
    }
}
