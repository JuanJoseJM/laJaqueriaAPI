package com.proyecto.laJaqueriaAPI.dao;

import com.proyecto.laJaqueriaAPI.model.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * The interface Reserva repository.
 */
public interface ReservaRepository extends JpaRepository<Reserva, Long> {
}