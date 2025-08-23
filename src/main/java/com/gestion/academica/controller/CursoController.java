package com.gestion.academica.controller;

import java.util.List;

import com.gestion.academica.dto.CursoDTO;
import com.gestion.academica.entity.Curso;
import com.gestion.academica.service.CursoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cursos")
public class CursoController {

    @Autowired
    private CursoService cursoService;

    // --- CRUD existentes ---
    @PostMapping
    public Curso crear(@RequestBody Curso curso) {
        return cursoService.crear(curso);
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

    // --- NUEVOS GET con DTOs ---
    // GET lista con filtros (?nombre=&descripcion=&profesorId=)
    @GetMapping
    public List<CursoDTO> listar(
            @RequestParam(required = false) String nombre,
            @RequestParam(required = false) String descripcion,
            @RequestParam(required = false) Long profesorId) {
        return cursoService.listarDTO(nombre, descripcion, profesorId);
    }

    // GET por ID devolviendo DTO
    @GetMapping("/{id}")
    public CursoDTO obtener(@PathVariable Long id) {
        return cursoService.obtenerDTO(id);
    }
}
