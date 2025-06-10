package com.proyecto.laJaqueriaAPI.services;

import com.proyecto.laJaqueriaAPI.dao.EventoRepository;
import com.proyecto.laJaqueriaAPI.model.Evento;
import com.proyecto.laJaqueriaAPI.model.Socio;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Servicio que gestiona la lógica de negocio relacionada con los eventos.
 */
@Service
public class EventoService {

    private final EventoRepository eventoRepository;

    /**
     * Constructor que inyecta el repositorio de eventos.
     * @param eventoRepository repositorio de acceso a datos de Evento
     */
    public EventoService(EventoRepository eventoRepository) {
        this.eventoRepository = eventoRepository;
    }

    /**
     * Obtiene todos los eventos existentes.
     * @return lista de eventos
     */
    public List<Evento> getAllEventos() {
        return eventoRepository.findAll();
    }

    /**
     * Busca un evento por su ID.
     * @param idEvento identificador del evento
     * @return objeto Evento o null si no se encuentra
     */
    public Evento getEventoById(Long idEvento) {
        Optional<Evento> evento = eventoRepository.findById(idEvento);
        return evento.orElse(null);
    }

    /**
     * Crea un nuevo evento con los datos proporcionados.
     * @param nombre nombre del evento
     * @param descripcion descripción del evento
     * @param fecha fecha del evento
     * @return evento creado
     */
    public Evento createEvento(String nombre, String descripcion, String fecha) {
        Evento evento = new Evento(nombre, descripcion, fecha);
        return eventoRepository.save(evento);
    }

    /**
     * Actualiza los datos de un evento existente.
     * @param idEvento ID del evento a actualizar
     * @param nombre nuevo nombre
     * @param descripcion nueva descripción
     * @param fecha nueva fecha
     * @return evento actualizado o null si no existe
     */
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

    /**
     * Elimina un evento por su ID.
     * @param idEvento ID del evento a eliminar
     */
    public void deleteEvento(Long idEvento) {
        eventoRepository.deleteById(idEvento);
    }

    /**
     * Inscribe un socio en un evento.
     * (Actualmente el socio no se recupera realmente de la base de datos).
     * @param idEvento ID del evento
     * @param idSocio ID del socio
     */
    public void inscribirSocio(Long idEvento, Long idSocio) {
        Evento evento = eventoRepository.findById(idEvento).orElse(null);
        Socio socio = new Socio(); // TODO: Obtener el socio de la base de datos por idSocio

        if (evento != null && socio != null) {
            evento.getSocios().add(socio);
            eventoRepository.save(evento);
        }
    }
}