package com.proyecto.laJaqueriaAPI.model;

import jakarta.persistence.*;

/**
 * Entidad que representa a un socio dentro del sistema.
 *
 * Un socio tiene un nombre, apellidos, edad y un identificador único.
 */
@Entity
public class Socio {

    /** Clave primaria del socio */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idSocio;

    /** Nombre del socio */
    private String nombre = "";

    /** Apellidos del socio */
    private String apellidos = "";

    /** Edad del socio */
    private int edad;

    /**
     * Constructor con todos los campos.
     * @param nombre nombre del socio
     * @param apellidos apellidos del socio
     * @param edad edad del socio
     */
    public Socio(String nombre, String apellidos, int edad) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.edad = edad;
    }

    /** Constructor vacío requerido por JPA */
    public Socio() {}

    /** @return nombre del socio */
    public String getNombre() {
        return nombre;
    }

    /** @param nombre nombre a establecer */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /** @return apellidos del socio */
    public String getApellidos() {
        return apellidos;
    }

    /** @param apellidos apellidos a establecer */
    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    /** @return edad del socio */
    public int getEdad() {
        return edad;
    }

    /** @param edad edad a establecer */
    public void setEdad(int edad) {
        this.edad = edad;
    }

    /** @return identificador único del socio */
    public Long getIdSocio() {
        return idSocio;
    }

    /** @param idSocio ID a establecer */
    public void setIdSocio(Long idSocio) {
        this.idSocio = idSocio;
    }
}