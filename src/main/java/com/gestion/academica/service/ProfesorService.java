package com.gestion.academica.service;

import java.util.*;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gestion.academica.dto.ProfesorDTO;
import com.gestion.academica.entity.Profesor;
import com.gestion.academica.repository.ProfesorRepository;

@Service
public class ProfesorService {

    @Autowired
    private ProfesorRepository profesorRepository;

    // ===== CRUD existente (sin tocar) =====
    public Profesor crear(Profesor profesor) {
        return profesorRepository.save(profesor);
    }

    public List<Profesor> obtenerTodos() {
        return profesorRepository.findAll();
    }

    public Optional<Profesor> obtenerPorId(Long id) {
        return profesorRepository.findById(id);
    }

    public Profesor actualizar(Long id, Profesor profesorActualizado) {
        Profesor profesor = profesorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Profesor no encontrado"));

        profesor.setNombre(profesorActualizado.getNombre());
        profesor.setApellidos(profesorActualizado.getApellidos());
        profesor.setDireccion(profesorActualizado.getDireccion());
        profesor.setTelefono(profesorActualizado.getTelefono());

        return profesorRepository.save(profesor);
    }

    public void eliminar(Long id) {
        if (!profesorRepository.existsById(id)) {
            throw new RuntimeException("Profesor no encontrado");
        }
        profesorRepository.deleteById(id);
    }

    // ===== NUEVO: DTOs + FILTROS para GET =====

    // Listado con filtros opcionales (?nombre=...&apellidos=...&telefono=...)
    public List<ProfesorDTO> listarDTO(String nombre, String apellidos, String telefono) {
        List<Profesor> data;

        if (nombre != null && !nombre.isBlank()) {
            data = profesorRepository.findByNombreContainingIgnoreCase(nombre);
        } else if (apellidos != null && !apellidos.isBlank()) {
            data = profesorRepository.findByApellidosContainingIgnoreCase(apellidos);
        } else if (telefono != null && !telefono.isBlank()) {
            data = profesorRepository.findByTelefonoContaining(telefono);
        } else {
            data = profesorRepository.findAll();
        }

        return data.stream().map(this::toDTO).collect(Collectors.toList());
    }

    // GET por ID devolviendo DTO
    public ProfesorDTO obtenerDTO(Long id) {
        Profesor p = profesorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Profesor " + id + " no encontrado"));
        return toDTO(p);
    }

    // Mapper entidad -> DTO (no exponemos dirección)
    private ProfesorDTO toDTO(Profesor p) {
        String nombreCompleto = (p.getNombre() == null ? "" : p.getNombre()) + " " +
                                (p.getApellidos() == null ? "" : p.getApellidos());
        // Ojo: tu getter del id es getidProfesor() (con i minúscula)
        Long id = p.getidProfesor();
        return new ProfesorDTO(id, nombreCompleto.trim(), p.getTelefono());
    }
}
