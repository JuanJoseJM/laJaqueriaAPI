package com.proyecto.laJaqueriaAPI.model;

import jakarta.persistence.*;
import lombok.Data;

/**
 * Representa una sala disponible en el espacio de coworking.
 */
@Entity
@Data
public class Sala {

    /** Identificador único de la sala. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** Nombre de la sala. */
    private String nombre;

    /** Descripción de la sala. */
    private String descripcion;

    /** Indica si la sala está disponible para reserva. */
    private boolean disponible;
}
