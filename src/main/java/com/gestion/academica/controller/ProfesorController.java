package com.gestion.academica.controller;

import java.util.List;

import com.gestion.academica.dto.ProfesorDTO;
import com.gestion.academica.entity.Profesor;
import com.gestion.academica.service.ProfesorService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/profesores")
public class ProfesorController {

    @Autowired
    private ProfesorService profesorService;

    // --- CRUD existentes ---
    @PostMapping
    public Profesor crear(@RequestBody Profesor profesor) {
        return profesorService.crear(profesor);
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

    // --- NUEVOS GET con DTOs ---
    // GET lista con filtros (?nombre=&apellidos=&telefono=)
    @GetMapping
    public List<ProfesorDTO> listar(
            @RequestParam(required = false) String nombre,
            @RequestParam(required = false) String apellidos,
            @RequestParam(required = false) String telefono) {
        return profesorService.listarDTO(nombre, apellidos, telefono);
    }

    // GET por ID devolviendo DTO
    @GetMapping("/{id}")
    public ProfesorDTO obtener(@PathVariable Long id) {
        return profesorService.obtenerDTO(id);
    }
}
