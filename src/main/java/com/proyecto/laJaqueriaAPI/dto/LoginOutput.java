package com.proyecto.laJaqueriaAPI.dto;

/**
 * Objeto de salida para el proceso de autenticación.
 *
 * Contiene la respuesta que se entrega al usuario al iniciar sesión correctamente,
 * incluyendo el token de acceso y el correo asociado.
 */
public class LoginOutput {

    /** Código de acceso generado (token JWT) */
    private String accesscode;

    /** Correo electrónico del usuario autenticado */
    private String email;

    /**
     * Constructor con parámetros.
     * @param accesscode token de autenticación
     * @param email correo electrónico del usuario
     */
    public LoginOutput(String accesscode, String email) {
        this.accesscode = accesscode;
        this.email = email;
    }

    /**
     * Obtiene el token de acceso.
     * @return token JWT
     */
    public String getAccesscode() { return accesscode; }

    /**
     * Obtiene el email del usuario autenticado.
     * @return correo electrónico
     */
    public String getEmail() { return email; }
}