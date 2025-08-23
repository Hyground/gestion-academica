package com.gestion.academica.dto;

import java.time.LocalDate;

public record InscripcionDTO(
        Long id,
        LocalDate fecha,
        Long estudianteId,
        String estudianteNombre,
        Long cursoId,
        String cursoNombre
) {}
