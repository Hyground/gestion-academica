package com.gestion.academica.service;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.gestion.academica.entity.Curso;
import com.gestion.academica.repository.CursoRepository;

@Service
public class CursoService {
    @Autowired
    private CursoRepository cursoRepository;

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
}
