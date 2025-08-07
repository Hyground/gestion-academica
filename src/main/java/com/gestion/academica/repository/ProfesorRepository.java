package com.gestion.academica.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.gestion.academica.entity.Profesor;

public interface ProfesorRepository extends JpaRepository<Profesor, Long> {

    
}
