
package com.gestion.academica.dto;

public class EstudiantesPorCicloDTO {
    private final String ciclo;
    private final Long totalEstudiantes;

    public EstudiantesPorCicloDTO(String ciclo, Long totalEstudiantes) {
        this.ciclo = ciclo;
        this.totalEstudiantes = totalEstudiantes;
    }
    public String getCiclo() { return ciclo; }
    public Long getTotalEstudiantes() { return totalEstudiantes; }
}
