package com.proyecto.laJaqueriaAPI.dao;

import com.proyecto.laJaqueriaAPI.model.Sala;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * The interface Sala repository.
 */
public interface SalaRepository extends JpaRepository<Sala, Long> {
}