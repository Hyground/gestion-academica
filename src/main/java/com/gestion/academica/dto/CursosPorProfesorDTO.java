
package com.gestion.academica.dto;

public class CursosPorProfesorDTO {
    private final Long profesorId;
    private final String profesorNombreCompleto;
    private final Long totalCursos;

    public CursosPorProfesorDTO(Long profesorId, String profesorNombreCompleto, Long totalCursos) {
        this.profesorId = profesorId;
        this.profesorNombreCompleto = profesorNombreCompleto;
        this.totalCursos = totalCursos;
    }
    public Long getProfesorId() { return profesorId; }
    public String getProfesorNombreCompleto() { return profesorNombreCompleto; }
    public Long getTotalCursos() { return totalCursos; }
}
