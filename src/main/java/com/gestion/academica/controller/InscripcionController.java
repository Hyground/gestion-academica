package com.gestion.academica.controller;

import java.util.List;

import com.gestion.academica.dto.InscripcionDTO;
import com.gestion.academica.entity.Inscripcion;
import com.gestion.academica.service.InscripcionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/inscripciones")
public class InscripcionController {

    @Autowired
    private InscripcionService inscripcionService;

    // --- CRUD existentes ---
    @PostMapping
    public Inscripcion crear(@RequestBody Inscripcion inscripcion) {
        return inscripcionService.crear(inscripcion);
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

    // --- NUEVOS GET con DTOs ---
    // GET lista con filtros (?cursoId=&estudianteId=)
    @GetMapping
    public List<InscripcionDTO> listar(
            @RequestParam(required = false) Long cursoId,
            @RequestParam(required = false) Long estudianteId) {
        return inscripcionService.listarDTO(cursoId, estudianteId);
    }

    // GET por ID devolviendo DTO
    @GetMapping("/{id}")
    public InscripcionDTO obtener(@PathVariable Long id) {
        return inscripcionService.obtenerDTO(id);
    }
}
