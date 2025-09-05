
package com.gestion.academica.service.impl;

import com.gestion.academica.dto.CursosPorProfesorDTO;
import com.gestion.academica.dto.EstudiantesPorCicloDTO;
import com.gestion.academica.dto.NotaPromedioPorCursoDTO;
import com.gestion.academica.repository.CursoRepository;
import com.gestion.academica.repository.InscripcionRepository;
import com.gestion.academica.service.ReporteService;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class ReporteServiceImpl implements ReporteService {

    private final CursoRepository cursoRepository;
    private final InscripcionRepository inscripcionRepository;

    public ReporteServiceImpl(CursoRepository cursoRepository, InscripcionRepository inscripcionRepository) {
        this.cursoRepository = cursoRepository;
        this.inscripcionRepository = inscripcionRepository;
    }

    @Override
    public List<CursosPorProfesorDTO> cursosPorProfesor() {
        return cursoRepository.contarCursosPorProfesor();
    }

    @Override
    public List<NotaPromedioPorCursoDTO> notaPromedioPorCurso() {
        return inscripcionRepository.notaPromedioPorCurso();
    }

    @Override
    public List<EstudiantesPorCicloDTO> estudiantesPorCiclo() {
        return inscripcionRepository.estudiantesPorCiclo();
    }

    @Override
    public List<NotaPromedioPorCursoDTO> topCursosPorNotaPromedio(int top) {
        return inscripcionRepository.topCursosPorNotaPromedio(PageRequest.of(0, Math.max(top, 1)));
    }
}
