package com.proyecto.laJaqueriaAPI.controller;

import com.proyecto.laJaqueriaAPI.model.Sala;
import com.proyecto.laJaqueriaAPI.dao.SalaRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador REST para gestionar las salas del coworking.
 */
@RestController
@RequestMapping("/salas")
@CrossOrigin
public class SalaController {

    private final SalaRepository salaRepository;

    /**
     * Constructor del controlador de salas.
     * @param salaRepository Repositorio de salas
     */
    public SalaController(SalaRepository salaRepository) {
        this.salaRepository = salaRepository;
    }

    /**
     * Lista todas las salas existentes.
     * @return Lista de salas
     */
    @GetMapping
    public List<Sala> listarSalas() {
        return salaRepository.findAll();
    }

    /**
     * Crea una nueva sala.
     * @param sala Sala a crear
     * @return Sala guardada
     */
    @PostMapping
    public Sala crearSala(@RequestBody Sala sala) {
        return salaRepository.save(sala);
    }

    /**
     * Actualiza una sala existente.
     * @param id ID de la sala a actualizar
     * @param sala Sala con los datos actualizados
     * @return Sala actualizada
     */
    @PutMapping("/{id}")
    public Sala actualizarSala(@PathVariable Long id, @RequestBody Sala sala) {
        sala.setId(id);
        return salaRepository.save(sala);
    }

    /**
     * Elimina una sala por su ID.
     * @param id ID de la sala a eliminar
     */
    @DeleteMapping("/{id}")
    public void eliminarSala(@PathVariable Long id) {
        salaRepository.deleteById(id);
    }
}