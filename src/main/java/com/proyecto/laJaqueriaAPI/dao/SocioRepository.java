package com.proyecto.laJaqueriaAPI.dao;

import com.proyecto.laJaqueriaAPI.model.Socio;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

/**
 * The interface Socio repository.
 */
public interface SocioRepository extends JpaRepository<Socio, Long> {
    /**
     * Find by nombre containing list.
     *
     * @param nombre the nombre
     * @return the list
     */
    List<Socio> findByNombreContaining(String nombre); // Buscar socios por nombre
}
