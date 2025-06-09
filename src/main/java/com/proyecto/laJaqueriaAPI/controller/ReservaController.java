package com.proyecto.laJaqueriaAPI.controller;

import com.proyecto.laJaqueriaAPI.model.Reserva;
import com.proyecto.laJaqueriaAPI.dao.ReservaRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador REST para gestionar las reservas de salas.
 */
@RestController
@RequestMapping("/reservas")
@CrossOrigin
public class ReservaController {

    private final ReservaRepository reservaRepository;

    /**
     * Constructor del controlador de reservas.
     * @param reservaRepository Repositorio de reservas
     */
    public ReservaController(ReservaRepository reservaRepository) {
        this.reservaRepository = reservaRepository;
    }

    /**
     * Lista todas las reservas realizadas.
     * @return Lista de reservas
     */
    @GetMapping
    public List<Reserva> listarReservas() {
        return reservaRepository.findAll();
    }

    /**
     * Crea una nueva reserva de sala.
     * @param reserva Reserva a crear
     * @return Reserva guardada
     */
    @PostMapping
    public Reserva crearReserva(@RequestBody Reserva reserva) {
        return reservaRepository.save(reserva);
    }

    /**
     * Cancela una reserva por su ID.
     * @param id ID de la reserva a cancelar
     */
    @DeleteMapping("/{id}")
    public void cancelarReserva(@PathVariable Long id) {
        reservaRepository.deleteById(id);
    }
}