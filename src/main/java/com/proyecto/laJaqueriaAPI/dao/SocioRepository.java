package com.proyecto.laJaqueriaAPI.dao;

import com.proyecto.laJaqueriaAPI.model.Socio;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface SocioRepository extends JpaRepository<Socio, Long> {
    List<Socio> findByNombreContaining(String nombre); // Buscar socios por nombre
}
