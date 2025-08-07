package com.gestion.academica.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.gestion.academica.entity.Curso;

public interface CursoRepository extends JpaRepository<Curso, Long> {

    
}
