package com.proyecto.laJaqueriaAPI.controller;

import com.proyecto.laJaqueriaAPI.model.Evento;
import com.proyecto.laJaqueriaAPI.services.EventoService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador REST para gestionar eventos del coworking.
 *
 * Permite a administradores crear, modificar y eliminar eventos.
 * Los socios pueden consultar e inscribirse a eventos.
 */
@RestController
@RequestMapping("/eventos")
public class EventoController {

    private final EventoService eventoService;

    /**
     * Constructor que inyecta el servicio de eventos.
     * @param eventoService Servicio de lógica de eventos
     */
    public EventoController(EventoService eventoService) {
        this.eventoService = eventoService;
    }

    /**
     * Obtiene todos los eventos registrados en el sistema.
     * @return lista de eventos
     */
    @CrossOrigin
    @GetMapping
    public List<Evento> getEventos() {
        return eventoService.getAllEventos();
    }

    /**
     * Obtiene un evento específico por su ID.
     * @param idEvento ID del evento
     * @return objeto Evento
     */
    @CrossOrigin
    @GetMapping("/{idEvento}")
    public Evento getEventoById(@PathVariable Long idEvento) {
        return eventoService.getEventoById(idEvento);
    }

    /**
     * Crea un nuevo evento. Solo accesible por administradores.
     * @param evento objeto Evento con los datos necesarios
     * @return evento creado
     */
    @PreAuthorize("hasRole('ADMIN')")
    @CrossOrigin
    @PostMapping
    public Evento crearEvento(@RequestBody Evento evento) {
        return eventoService.createEvento(evento.getNombre(), evento.getDescripcion(), evento.getFecha());
    }

    /**
     * Actualiza un evento existente por su ID. Solo admins.
     * @param idEvento ID del evento
     * @param evento objeto Evento actualizado
     * @return evento actualizado
     */
    @PreAuthorize("hasRole('ADMIN')")
    @CrossOrigin
    @PutMapping("/{idEvento}")
    public Evento actualizarEvento(@PathVariable Long idEvento, @RequestBody Evento evento) {
        return eventoService.updateEvento(idEvento, evento.getNombre(), evento.getDescripcion(), evento.getFecha());
    }

    /**
     * Elimina un evento existente. Solo accesible por administradores.
     * @param idEvento ID del evento
     */
    @PreAuthorize("hasRole('ADMIN')")
    @CrossOrigin
    @DeleteMapping("/{idEvento}")
    public void eliminarEvento(@PathVariable Long idEvento) {
        eventoService.deleteEvento(idEvento);
    }

    /**
     * Inscribe un socio en un evento determinado.
     * @param idEvento ID del evento
     * @param idSocio ID del socio
     */
    @CrossOrigin
    @PostMapping("/{idEvento}/inscribir")
    public void inscribirSocio(@PathVariable Long idEvento, @RequestBody Long idSocio) {
        eventoService.inscribirSocio(idEvento, idSocio);
    }

    /**
     * Genera un informe PDF de los eventos. Solo admins.
     * @return mensaje de confirmación
     */
    @PreAuthorize("hasRole('ADMIN')")
    @CrossOrigin
    @GetMapping("/informe")
    public String generarInformePdf() {
        // Aquí puedes implementar la generación del PDF
        return "Informe PDF generado";
    }
}