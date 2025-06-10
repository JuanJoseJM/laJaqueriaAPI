package com.proyecto.laJaqueriaAPI.dto;

/**
 * Objeto de respuesta para el proceso de login.
 * <p>
 * Contiene el token de acceso (accesscode) y el correo electrónico del usuario
 * que ha iniciado sesión correctamente.
 */
public class LoginResponse {

    /** Token de autenticación generado tras el login */
    private String accesscode;

    /** Correo electrónico del usuario autenticado */
    private String email;

    /**
     * Constructor para crear la respuesta de login.
     *
     * @param accesscode token JWT generado
     * @param email      correo electrónico del usuario
     */
    public LoginResponse(String accesscode, String email) {
        this.accesscode = accesscode;
        this.email = email;
    }

    /**
     * Devuelve el token de acceso del usuario.
     *
     * @return accesscode (JWT)
     */
    public String getAccesscode() {
        return accesscode;
    }

    /**
     * Devuelve el correo electrónico del usuario autenticado.
     *
     * @return email email
     */
    public String getEmail() {
        return email;
    }
}