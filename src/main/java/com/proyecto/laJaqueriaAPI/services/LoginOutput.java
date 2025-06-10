package com.proyecto.laJaqueriaAPI.services;

/**
 * Clase auxiliar para representar la salida del proceso de login.
 * <p>
 * Contiene el token JWT generado y el correo electrónico del usuario autenticado.
 */
public class LoginOutput {

    /**
     * Código de acceso (token JWT)
     */
    String accesscode;

    /**
     * Correo electrónico del usuario
     */
    String email;

    /**
     * Constructor con parámetros.
     *
     * @param accesscode token generado
     * @param email      correo del usuario autenticado
     */
    public LoginOutput(String accesscode, String email) {
        this.accesscode = accesscode;
        this.email = email;
    }

    /**
     * Gets accesscode.
     *
     * @return token de acceso
     */
    public String getAccesscode() {
        return accesscode;
    }

    /**
     * Sets accesscode.
     *
     * @param accesscode nuevo token a establecer
     */
    public void setAccesscode(String accesscode) {
        this.accesscode = accesscode;
    }

    /**
     * Gets email.
     *
     * @return email del usuario
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets email.
     *
     * @param email correo electrónico a establecer
     */
    public void setEmail(String email) {
        this.email = email;
    }
}