package com.gestion.academica.controller;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.gestion.academica.entity.Inscripcion;
import com.gestion.academica.service.InscripcionService;


@RestController
@RequestMapping("/api/inscripciones")
public class InscripcionController {
    
    @Autowired
    private InscripcionService inscripcionService;

    @PostMapping
    public Inscripcion crear(@RequestBody Inscripcion inscripcion) {
        return inscripcionService.crear(inscripcion);
    }

    @GetMapping
    public List<Inscripcion> obtenerTodos() {
        return inscripcionService.obtenerTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Inscripcion> obtenerPorId(@PathVariable Long id) {
        return inscripcionService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public Inscripcion actualizar(@PathVariable Long id, @RequestBody Inscripcion actualizada) {
        return inscripcionService.actualizar(id, actualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        inscripcionService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
