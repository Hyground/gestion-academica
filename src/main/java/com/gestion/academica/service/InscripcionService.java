package com.gestion.academica.service;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.gestion.academica.entity.Inscripcion;
import com.gestion.academica.repository.InscripcionRepository;

@Service
public class InscripcionService {
    @Autowired
    private InscripcionRepository inscripcionRepository;

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
}
