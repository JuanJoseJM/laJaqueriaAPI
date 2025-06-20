package com.proyecto.laJaqueriaAPI.dto;

/**
 * Objeto de transferencia de datos (DTO) para representar la información básica de un socio.
 * <p>
 * Este DTO es usado para crear o actualizar socios desde el frontend o clientes externos.
 */
public class SocioDTO {

    /** Nombre del socio */
    private String nombre = "";

    /** Apellidos del socio */
    private String apellidos = "";

    /** Edad del socio */
    private int edad = 0;

    /**
     * Constructor completo con parámetros.
     *
     * @param nombre    nombre del socio
     * @param apellidos apellidos del socio
     * @param edad      edad del socio
     */
    public SocioDTO(String nombre, String apellidos, int edad) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.edad = edad;
    }

    /**
     * Gets nombre.
     *
     * @return nombre del socio
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Sets nombre.
     *
     * @param nombre nombre a establecer
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Gets apellidos.
     *
     * @return apellidos del socio
     */
    public String getApellidos() {
        return apellidos;
    }

    /**
     * Sets apellidos.
     *
     * @param apellidos apellidos a establecer
     */
    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    /**
     * Gets edad.
     *
     * @return edad del socio
     */
    public int getEdad() {
        return edad;
    }

    /**
     * Sets edad.
     *
     * @param edad edad a establecer
     */
    public void setEdad(int edad) {
        this.edad = edad;
    }
}