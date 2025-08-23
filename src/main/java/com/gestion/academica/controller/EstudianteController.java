package com.gestion.academica.controller;

import java.util.List;

import com.gestion.academica.dto.EstudianteDTO;
import com.gestion.academica.entity.Estudiante;
import com.gestion.academica.service.EstudianteService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/estudiantes")
public class EstudianteController {

    @Autowired
    private EstudianteService estudianteService;

    // --- CRUD existentes ---
    @PostMapping
    public Estudiante crear(@RequestBody Estudiante estudiante) {
        return estudianteService.crear(estudiante);
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

    // --- NUEVOS GET con DTOs ---
    // GET lista con filtros (?apellidos=&nombre=&telefono=)
    @GetMapping
    public List<EstudianteDTO> listar(
            @RequestParam(required = false) String apellidos,
            @RequestParam(required = false) String nombre,
            @RequestParam(required = false) String telefono) {
        return estudianteService.listarDTO(apellidos, nombre, telefono);
    }

    // GET por ID devolviendo DTO
    @GetMapping("/{id}")
    public EstudianteDTO obtener(@PathVariable Long id) {
        return estudianteService.obtenerDTO(id);
    }
}
