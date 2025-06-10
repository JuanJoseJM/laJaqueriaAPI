package com.proyecto.laJaqueriaAPI.dto;

/**
 * Objeto de transferencia de datos (DTO) utilizado en solicitudes de inicio de sesión.
 * <p>
 * Contiene las credenciales enviadas por el usuario: email y contraseña.
 */
public class LoginRequest {

    /** Correo electrónico del usuario */
    private String email;

    /** Contraseña del usuario */
    private String password;

    /**
     * Obtiene el correo electrónico del usuario.
     *
     * @return email email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Establece el correo electrónico del usuario.
     *
     * @param email dirección de correo
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Obtiene la contraseña del usuario.
     *
     * @return contraseña en texto plano
     */
    public String getPassword() {
        return password;
    }

    /**
     * Establece la contraseña del usuario.
     *
     * @param password contraseña sin cifrar
     */
    public void setPassword(String password) {
        this.password = password;
    }
}