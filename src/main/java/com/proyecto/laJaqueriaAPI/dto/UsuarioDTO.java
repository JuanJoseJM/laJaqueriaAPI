package com.proyecto.laJaqueriaAPI.dto;

/**
 * DTO utilizado para transferir datos de un usuario entre cliente y servidor.
 *
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

    /** @return nombre del usuario */
    public String getNombre() { return nombre; }

    /** @param nombre nombre a establecer */
    public void setNombre(String nombre) { this.nombre = nombre; }

    /** @return apellidos del usuario */
    public String getApellidos() { return apellidos; }

    /** @param apellidos apellidos a establecer */
    public void setApellidos(String apellidos) { this.apellidos = apellidos; }

    /** @return email del usuario */
    public String getEmail() { return email; }

    /** @param email correo electrónico a establecer */
    public void setEmail(String email) { this.email = email; }

    /** @return contraseña del usuario */
    public String getPassword() { return password; }

    /** @param password contraseña a establecer */
    public void setPassword(String password) { this.password = password; }

    /** @return rol del usuario */
    public String getRol() { return rol; }

    /** @param rol rol a establecer (SOCIO o ADMIN) */
    public void setRol(String rol) { this.rol = rol; }
}