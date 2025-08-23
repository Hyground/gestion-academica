package com.gestion.academica.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.gestion.academica.entity.Curso;

public interface CursoRepository extends JpaRepository<Curso, Long> {
    List<Curso> findByNombreContainingIgnoreCase(String nombre);
    List<Curso> findByProfesor_IdProfesor(Long profesorId);
    List<Curso> findByDescripcionContainingIgnoreCase(String descripcion);
}
