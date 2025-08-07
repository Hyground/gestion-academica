package com.gestion.academica.controller;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.gestion.academica.entity.Estudiante;
import com.gestion.academica.service.EstudianteService;


@RestController
@RequestMapping("/api/estudiantes")
public class EstudianteController {
    
    @Autowired
    private EstudianteService estudianteService;

    @PostMapping
    public Estudiante crear(@RequestBody Estudiante estudiante) {
        return estudianteService.crear(estudiante);
    }

    @GetMapping
    public List<Estudiante> obtenerTodos() {
        return estudianteService.obtenerTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Estudiante> obtenerPorId(@PathVariable Long id) {
        return estudianteService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public Estudiante actualizar(@PathVariable Long id, @RequestBody Estudiante actualizado) {
        return estudianteService.actualizar(id, actualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        estudianteService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
