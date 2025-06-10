package com.proyecto.laJaqueriaAPI.services;

/**
 * Clase auxiliar para representar la salida del proceso de login.
 *
 * Contiene el token JWT generado y el correo electrónico del usuario autenticado.
 */
public class LoginOutput {

    /** Código de acceso (token JWT) */
    String accesscode;

    /** Correo electrónico del usuario */
    String email;

    /**
     * Constructor con parámetros.
     * @param accesscode token generado
     * @param email correo del usuario autenticado
     */
    public LoginOutput(String accesscode, String email) {
        this.accesscode = accesscode;
        this.email = email;
    }

    /** @return token de acceso */
    public String getAccesscode() {
        return accesscode;
    }

    /** @param accesscode nuevo token a establecer */
    public void setAccesscode(String accesscode) {
        this.accesscode = accesscode;
    }

    /** @return email del usuario */
    public String getEmail() {
        return email;
    }

    /** @param email correo electrónico a establecer */
    public void setEmail(String email) {
        this.email = email;
    }
}