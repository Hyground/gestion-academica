package com.gestion.academica.service;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.gestion.academica.entity.Profesor;
import com.gestion.academica.repository.ProfesorRepository;


@Service
public class ProfesorService {

    @Autowired
    private ProfesorRepository profesorRepository;

    // Crear profesor
    public Profesor crear(Profesor profesor) {
        return profesorRepository.save(profesor);
    }

    // Obtener todos los profesores
    public List<Profesor> obtenerTodos() {
        return profesorRepository.findAll();
    }

    // Obtener profesor por ID
    public Optional<Profesor> obtenerPorId(Long id) {
        return profesorRepository.findById(id);
    }

    // Actualizar profesor
    public Profesor actualizar(Long id, Profesor profesorActualizado) {
        Profesor profesor = profesorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Profesor no encontrado"));

        profesor.setNombre(profesorActualizado.getNombre());
        profesor.setApellidos(profesorActualizado.getApellidos());
        profesor.setDireccion(profesorActualizado.getDireccion());
        profesor.setTelefono(profesorActualizado.getTelefono());

        return profesorRepository.save(profesor);
    }

    // Eliminar profesor
    public void eliminar(Long id) {
        if (!profesorRepository.existsById(id)) {
            throw new RuntimeException("Profesor no encontrado");
        }
        profesorRepository.deleteById(id);
    }
}
