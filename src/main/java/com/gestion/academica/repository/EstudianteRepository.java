package com.gestion.academica.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.gestion.academica.entity.Estudiante;

public interface EstudianteRepository extends JpaRepository<Estudiante, Long> {

    
}
