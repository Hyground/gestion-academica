package com.gestion.academica.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.gestion.academica.entity.Profesor;

public interface ProfesorRepository extends JpaRepository<Profesor, Long> {
    List<Profesor> findByNombreContainingIgnoreCase(String nombre);
    List<Profesor> findByApellidosContainingIgnoreCase(String apellido);
    List<Profesor> findByTelefonoContaining(String telefono);
}
