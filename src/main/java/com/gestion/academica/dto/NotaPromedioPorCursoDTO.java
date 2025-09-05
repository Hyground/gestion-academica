
package com.gestion.academica.dto;

public class NotaPromedioPorCursoDTO {
    private final Long cursoId;
    private final String cursoNombre;
    private final Double notaPromedio;

    public NotaPromedioPorCursoDTO(Long cursoId, String cursoNombre, Double notaPromedio) {
        this.cursoId = cursoId;
        this.cursoNombre = cursoNombre;
        this.notaPromedio = notaPromedio;
    }
    public Long getCursoId() { return cursoId; }
    public String getCursoNombre() { return cursoNombre; }
    public Double getNotaPromedio() { return notaPromedio; }
}
