package com.gestion.academica.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.gestion.academica.entity.Estudiante;

public interface EstudianteRepository extends JpaRepository<Estudiante, Long> {
    List<Estudiante> findByApellidosContainingIgnoreCase(String apellido);
    List<Estudiante> findByNombreContainingIgnoreCase(String nombre);
    List<Estudiante> findByTelefonoContaining(String telefono);
}
