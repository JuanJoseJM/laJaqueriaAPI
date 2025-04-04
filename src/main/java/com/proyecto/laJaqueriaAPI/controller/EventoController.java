package com.proyecto.laJaqueriaAPI.controller;

import com.proyecto.laJaqueriaAPI.model.Evento;
import com.proyecto.laJaqueriaAPI.services.EventoService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/eventos")
public class EventoController {

    private final EventoService eventoService;

    public EventoController(EventoService eventoService) {
        this.eventoService = eventoService;
    }

    // Consulta de todos los eventos (accesible por todos)
    @CrossOrigin
    @GetMapping
    public List<Evento> getEventos() {
        return eventoService.getAllEventos();
    }

    // Consulta de un evento específico por su ID (accesible por todos)
    @CrossOrigin
    @GetMapping("/{idEvento}")
    public Evento getEventoById(@PathVariable Long idEvento) {
        return eventoService.getEventoById(idEvento);
    }

    // Alta de evento (solo administradores)
    @PreAuthorize("hasRole('ADMIN')")
    @CrossOrigin
    @PostMapping
    public Evento crearEvento(@RequestBody Evento evento) {
        return eventoService.createEvento(evento.getNombre(), evento.getDescripcion(), evento.getFecha());
    }

    // Modificación de evento (solo administradores)
    @PreAuthorize("hasRole('ADMIN')")
    @CrossOrigin
    @PutMapping("/{idEvento}")
    public Evento actualizarEvento(@PathVariable Long idEvento, @RequestBody Evento evento) {
        return eventoService.updateEvento(idEvento, evento.getNombre(), evento.getDescripcion(), evento.getFecha());
    }

    // Baja de evento (solo administradores)
    @PreAuthorize("hasRole('ADMIN')")
    @CrossOrigin
    @DeleteMapping("/{idEvento}")
    public void eliminarEvento(@PathVariable Long idEvento) {
        eventoService.deleteEvento(idEvento);
    }

    // Inscripción de un socio en un evento
    @CrossOrigin
    @PostMapping("/{idEvento}/inscribir")
    public void inscribirSocio(@PathVariable Long idEvento, @RequestBody Long idSocio) {
        eventoService.inscribirSocio(idEvento, idSocio);
    }

    // Informe PDF de los eventos (solo administradores)
    @PreAuthorize("hasRole('ADMIN')")
    @CrossOrigin
    @GetMapping("/informe")
    public String generarInformePdf() {
        // Aquí puedes implementar la generación del PDF
        return "Informe PDF generado";
    }
}
