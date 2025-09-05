package com.gestion.academica.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.gestion.academica.dto.CursosPorProfesorDTO;
import com.gestion.academica.entity.Curso;

public interface CursoRepository extends JpaRepository<Curso, Long> {
    List<Curso> findByNombreContainingIgnoreCase(String nombre);
    List<Curso> findByProfesor_IdProfesor(Long profesorId);
    List<Curso> findByDescripcionContainingIgnoreCase(String descripcion);

    @Query("""
           SELECT new com.gestion.academica.dto.CursosPorProfesorDTO(
             p.idProfesor,
             CONCAT(p.nombre, ' ', COALESCE(p.apellidos, '')),
             COUNT(c)
           )
           FROM Curso c
           JOIN c.profesor p
           GROUP BY p.idProfesor, p.nombre, p.apellidos
           ORDER BY COUNT(c) DESC
           """)
    List<CursosPorProfesorDTO> contarCursosPorProfesor();
}
