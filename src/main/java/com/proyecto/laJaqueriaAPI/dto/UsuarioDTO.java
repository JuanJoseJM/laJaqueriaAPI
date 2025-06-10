package com.proyecto.laJaqueriaAPI.dto;

/**
 * DTO utilizado para transferir datos de un usuario entre cliente y servidor.
 * <p>
 * Incluye nombre, apellidos, email, contraseña y rol.
 */
public class UsuarioDTO {

    /** Nombre del usuario */
    private String nombre;

    /** Apellidos del usuario */
    private String apellidos;

    /** Correo electrónico del usuario */
    private String email;

    /** Contraseña en texto plano */
    private String password;

    /** Rol del usuario (SOCIO o ADMIN) */
    private String rol;

    /**
     * Gets nombre.
     *
     * @return nombre del usuario
     */
    public String getNombre() { return nombre; }

    /**
     * Sets nombre.
     *
     * @param nombre nombre a establecer
     */
    public void setNombre(String nombre) { this.nombre = nombre; }

    /**
     * Gets apellidos.
     *
     * @return apellidos del usuario
     */
    public String getApellidos() { return apellidos; }

    /**
     * Sets apellidos.
     *
     * @param apellidos apellidos a establecer
     */
    public void setApellidos(String apellidos) { this.apellidos = apellidos; }

    /**
     * Gets email.
     *
     * @return email del usuario
     */
    public String getEmail() { return email; }

    /**
     * Sets email.
     *
     * @param email correo electrónico a establecer
     */
    public void setEmail(String email) { this.email = email; }

    /**
     * Gets password.
     *
     * @return contraseña del usuario
     */
    public String getPassword() { return password; }

    /**
     * Sets password.
     *
     * @param password contraseña a establecer
     */
    public void setPassword(String password) { this.password = password; }

    /**
     * Gets rol.
     *
     * @return rol del usuario
     */
    public String getRol() { return rol; }

    /**
     * Sets rol.
     *
     * @param rol rol a establecer (SOCIO o ADMIN)
     */
    public void setRol(String rol) { this.rol = rol; }
}