package com.proyecto.laJaqueriaAPI.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

/**
 * Representa una reserva realizada por un usuario para una sala.
 */
@Entity
@Data
public class Reserva {

    /** Identificador único de la reserva. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** Correo electrónico del usuario que realiza la reserva. */
    private String emailUsuario;

    /** Fecha y hora de inicio de la reserva. */
    private LocalDateTime fechaInicio;

    /** Fecha y hora de finalización de la reserva. */
    private LocalDateTime fechaFin;

    /** Sala que se ha reservado. */
    @ManyToOne
    @JoinColumn(name = "sala_id")
    private Sala sala;
}
