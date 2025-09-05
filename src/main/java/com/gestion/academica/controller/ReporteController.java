
package com.gestion.academica.controller;

import com.gestion.academica.dto.CursosPorProfesorDTO;
import com.gestion.academica.dto.EstudiantesPorCicloDTO;
import com.gestion.academica.dto.NotaPromedioPorCursoDTO;
import com.gestion.academica.service.ReporteService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reportes")
public class ReporteController {

    private final ReporteService reporteService;

    public ReporteController(ReporteService reporteService) {
        this.reporteService = reporteService;
    }

    // Reporte 1
    @GetMapping("/profesores/cursos")
    public List<CursosPorProfesorDTO> getCursosPorProfesor() {
        return reporteService.cursosPorProfesor();
    }

    // Reporte 2
    @GetMapping("/cursos/notas-promedio")
    public List<NotaPromedioPorCursoDTO> getNotaPromedioPorCurso() {
        return reporteService.notaPromedioPorCurso();
    }

    // Reporte 3
    @GetMapping("/inscripciones/por-ciclo")
    public List<EstudiantesPorCicloDTO> getEstudiantesPorCiclo() {
        return reporteService.estudiantesPorCiclo();
    }

    // Reporte 4 (top N, default 3)
    @GetMapping("/cursos/top-notas")
    public List<NotaPromedioPorCursoDTO> getTopCursosPorNotaPromedio(
            @RequestParam(defaultValue = "3") int top) {
        return reporteService.topCursosPorNotaPromedio(top);
    }
}
