package com.gestion.academica.controller;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.gestion.academica.entity.Curso;
import com.gestion.academica.service.CursoService;

@RestController
@RequestMapping("/api/cursos")
public class CursoController {
    
    @Autowired
    private CursoService cursoService;

    @PostMapping
    public Curso crear(@RequestBody Curso curso) {
        return cursoService.crear(curso);
    }

    @GetMapping
    public List<Curso> obtenerTodos() {
        return cursoService.obtenerTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Curso> obtenerPorId(@PathVariable Long id) {
        return cursoService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public Curso actualizar(@PathVariable Long id, @RequestBody Curso actualizado) {
        return cursoService.actualizar(id, actualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        cursoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
