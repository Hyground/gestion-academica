package com.gestion.academica.repository;

import com.gestion.academica.dto.EstudiantesPorCicloDTO;
import com.gestion.academica.dto.NotaPromedioPorCursoDTO;
import com.gestion.academica.entity.Inscripcion;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;


public interface InscripcionRepository extends JpaRepository<Inscripcion, Long> {
    List<Inscripcion> findByCurso_IdCurso(Long cursoId);
    List<Inscripcion> findByEstudiante_IdEstudiante(Long estudianteId);
    
    // Reporte 2: nota promedio por curso
    @Query("""
           SELECT new com.gestion.academica.dto.NotaPromedioPorCursoDTO(
             c.idCurso, c.nombre, AVG(i.notaFinal)
           )
           FROM Inscripcion i
           JOIN i.curso c
           WHERE i.notaFinal IS NOT NULL
           GROUP BY c.idCurso, c.nombre
           ORDER BY c.nombre ASC
           """)
    List<NotaPromedioPorCursoDTO> notaPromedioPorCurso();

    // Reporte 3: estudiantes por ciclo (derivado de fecha -> 'Ciclo YYYY')
    @Query("""
           SELECT new com.gestion.academica.dto.EstudiantesPorCicloDTO(
             CONCAT('Ciclo ', FUNCTION('to_char', i.fecha, 'YYYY')),
             COUNT(DISTINCT i.estudiante.idEstudiante)
           )
           FROM Inscripcion i
           GROUP BY FUNCTION('to_char', i.fecha, 'YYYY')
           ORDER BY FUNCTION('to_char', i.fecha, 'YYYY') ASC
           """)
    List<EstudiantesPorCicloDTO> estudiantesPorCiclo();

    // Reporte 4: top cursos por promedio (limitar con Pageable)
    @Query("""
           SELECT new com.gestion.academica.dto.NotaPromedioPorCursoDTO(
             c.idCurso, c.nombre, AVG(i.notaFinal)
           )
           FROM Inscripcion i
           JOIN i.curso c
           WHERE i.notaFinal IS NOT NULL
           GROUP BY c.idCurso, c.nombre
           ORDER BY AVG(i.notaFinal) DESC
           """)
    List<NotaPromedioPorCursoDTO> topCursosPorNotaPromedio(Pageable pageable);
}