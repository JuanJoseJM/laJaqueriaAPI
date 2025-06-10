package com.proyecto.laJaqueriaAPI.dao;

import com.proyecto.laJaqueriaAPI.model.Evento;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * The interface Evento repository.
 */
public interface EventoRepository extends JpaRepository<Evento, Long> {
}
