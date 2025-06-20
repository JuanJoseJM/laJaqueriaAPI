package com.proyecto.laJaqueriaAPI.dto;

/**
 * DTO de salida para representar un usuario ya registrado.
 * <p>
 * Se utiliza para mostrar información sin exponer la contraseña.
 */
public class UsuarioOutputDTO {

    /** Nombre del usuario */
    private String nombre;

    /** Apellidos del usuario */
    private String apellidos;

    /** Correo electrónico */
    private String email;

    /** Identificador único del usuario */
    private Long id;

    /**
     * Constructor completo con todos los campos.
     *
     * @param nombre    nombre del usuario
     * @param apellidos apellidos del usuario
     * @param email     correo electrónico
     * @param id        identificador del usuario
     */
    public UsuarioOutputDTO(String nombre, String apellidos, String email, Long id) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.email = email;
        this.id = id;
    }

    /**
     * Gets nombre.
     *
     * @return nombre del usuario
     */
    public String getNombre() { return nombre; }

    /**
     * Gets apellidos.
     *
     * @return apellidos del usuario
     */
    public String getApellidos() { return apellidos; }

    /**
     * Gets email.
     *
     * @return email del usuario
     */
    public String getEmail() { return email; }

    /**
     * Gets id.
     *
     * @return identificador único del usuario
     */
    public Long getId() { return id; }
}