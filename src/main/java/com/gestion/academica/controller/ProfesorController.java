package com.gestion.academica.controller;

import java.util.*;
import com.gestion.academica.service.ProfesorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import com.gestion.academica.entity.Profesor;

@RestController
@RequestMapping("/api/profesores")
public class ProfesorController {
    
    @Autowired
    private ProfesorService profesorService;

    @PostMapping
    public Profesor crear(@RequestBody Profesor profesor) {
        return profesorService.crear(profesor);
    }

    @GetMapping
    public List<Profesor> obtenerTodos() {
        return profesorService.obtenerTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Profesor> obtenerPorId(@PathVariable Long id) {
        return profesorService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public Profesor actualizar(@PathVariable Long id, @RequestBody Profesor actualizado) {
        return profesorService.actualizar(id, actualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        profesorService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
    
}

