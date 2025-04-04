package com.proyecto.laJaqueriaAPI.services;

import com.proyecto.laJaqueriaAPI.dao.EventoRepository;
import com.proyecto.laJaqueriaAPI.model.Evento;
import com.proyecto.laJaqueriaAPI.model.Socio;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventoService {

    private final EventoRepository eventoRepository;

    public EventoService(EventoRepository eventoRepository) {
        this.eventoRepository = eventoRepository;
    }

    public List<Evento> getAllEventos() {
        return eventoRepository.findAll();
    }

    public Evento getEventoById(Long idEvento) {
        Optional<Evento> evento = eventoRepository.findById(idEvento);
        return evento.orElse(null);
    }

    public Evento createEvento(String nombre, String descripcion, String fecha) {
        Evento evento = new Evento(nombre, descripcion, fecha);
        return eventoRepository.save(evento);
    }

    public Evento updateEvento(Long idEvento, String nombre, String descripcion, String fecha) {
        Evento evento = eventoRepository.findById(idEvento).orElse(null);
        if (evento != null) {
            evento.setNombre(nombre);
            evento.setDescripcion(descripcion);
            evento.setFecha(fecha);
            return eventoRepository.save(evento);
        }
        return null;
    }

    public void deleteEvento(Long idEvento) {
        eventoRepository.deleteById(idEvento);
    }

    public void inscribirSocio(Long idEvento, Long idSocio) {
        Evento evento = eventoRepository.findById(idEvento).orElse(null);
        Socio socio = new Socio(); // Obtener el socio de la base de datos por idSocio

        if (evento != null && socio != null) {
            evento.getSocios().add(socio);
            eventoRepository.save(evento);
        }
    }
}