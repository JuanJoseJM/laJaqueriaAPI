package com.proyecto.laJaqueriaAPI.model;

import jakarta.persistence.*;
import java.util.List;

/**
 * Entidad que representa un evento dentro del espacio de coworking.
 *
 * Un evento tiene un nombre, una descripción, una fecha, y una lista de socios inscritos.
 */
@Entity
public class Evento {

    /** Identificador único del evento (clave primaria) */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idEvento;

    /** Nombre del evento */
    private String nombre;

    /** Descripción del evento */
    private String descripcion;

    /** Fecha del evento (formato String) */
    private String fecha;

    /**
     * Lista de socios inscritos al evento.
     *
     * Relación muchos a muchos con la entidad Socio.
     */
    @ManyToMany
    @JoinTable(
            name = "evento_socio",
            joinColumns = @JoinColumn(name = "idEvento"),
            inverseJoinColumns = @JoinColumn(name = "idSocio")
    )
    private List<Socio> socios;

    /** Constructor vacío requerido por JPA */
    public Evento() {}

    /**
     * Constructor con parámetros principales.
     * @param nombre nombre del evento
     * @param descripcion descripción del evento
     * @param fecha fecha del evento
     */
    public Evento(String nombre, String descripcion, String fecha) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.fecha = fecha;
    }

    public Long getIdEvento() {
        return idEvento;
    }

    public void setIdEvento(Long idEvento) {
        this.idEvento = idEvento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public List<Socio> getSocios() {
        return socios;
    }

    public void setSocios(List<Socio> socios) {
        this.socios = socios;
    }
}