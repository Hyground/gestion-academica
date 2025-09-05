
package com.gestion.academica.service;

import com.gestion.academica.dto.CursosPorProfesorDTO;
import com.gestion.academica.dto.EstudiantesPorCicloDTO;
import com.gestion.academica.dto.NotaPromedioPorCursoDTO;
import java.util.List;

public interface ReporteService {
    List<CursosPorProfesorDTO> cursosPorProfesor();
    List<NotaPromedioPorCursoDTO> notaPromedioPorCurso();
    List<EstudiantesPorCicloDTO> estudiantesPorCiclo();
    List<NotaPromedioPorCursoDTO> topCursosPorNotaPromedio(int top);
}
