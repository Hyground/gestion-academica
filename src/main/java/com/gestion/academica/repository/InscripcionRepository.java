package com.gestion.academica.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.gestion.academica.entity.Inscripcion;

public interface InscripcionRepository extends JpaRepository<Inscripcion, Long> {
    List<Inscripcion> findByCurso_IdCurso(Long cursoId);
    List<Inscripcion> findByEstudiante_IdEstudiante(Long estudianteId);
    
}
