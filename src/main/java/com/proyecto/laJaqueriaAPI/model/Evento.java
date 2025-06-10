package com.proyecto.laJaqueriaAPI.model;

import jakarta.persistence.*;
import java.util.List;

/**
 * Entidad que representa un evento dentro del espacio de coworking.
 * <p>
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

    /**
     * Constructor vacío requerido por JPA
     */
    public Evento() {}

    /**
     * Constructor con parámetros principales.
     *
     * @param nombre      nombre del evento
     * @param descripcion descripción del evento
     * @param fecha       fecha del evento
     */
    public Evento(String nombre, String descripcion, String fecha) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.fecha = fecha;
    }

    /**
     * Gets id evento.
     *
     * @return the id evento
     */
    public Long getIdEvento() {
        return idEvento;
    }

    /**
     * Sets id evento.
     *
     * @param idEvento the id evento
     */
    public void setIdEvento(Long idEvento) {
        this.idEvento = idEvento;
    }

    /**
     * Gets nombre.
     *
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Sets nombre.
     *
     * @param nombre the nombre
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Gets descripcion.
     *
     * @return the descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Sets descripcion.
     *
     * @param descripcion the descripcion
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * Gets fecha.
     *
     * @return the fecha
     */
    public String getFecha() {
        return fecha;
    }

    /**
     * Sets fecha.
     *
     * @param fecha the fecha
     */
    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    /**
     * Gets socios.
     *
     * @return the socios
     */
    public List<Socio> getSocios() {
        return socios;
    }

    /**
     * Sets socios.
     *
     * @param socios the socios
     */
    public void setSocios(List<Socio> socios) {
        this.socios = socios;
    }
}