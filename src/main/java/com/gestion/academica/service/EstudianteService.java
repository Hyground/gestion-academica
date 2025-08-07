package com.gestion.academica.service;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.gestion.academica.entity.Estudiante;
import com.gestion.academica.repository.EstudianteRepository;

@Service
public class EstudianteService {
    @Autowired
    private EstudianteRepository estudianteRepository;

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
}
