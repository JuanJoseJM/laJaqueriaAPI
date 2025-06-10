package com.proyecto.laJaqueriaAPI.dto;

/**
 * Objeto de transferencia de datos (DTO) para el proceso de inicio de sesión.
 * <p>
 * Contiene las credenciales que el usuario envía al sistema:
 * - usuario (correo electrónico o nombre de usuario)
 * - password (contraseña sin cifrar)
 */
public class LoginDTO {
    private String usuario;
    private String password;

    /**
     * Obtiene el nombre de usuario o correo del solicitante.
     *
     * @return usuario usuario
     */
    public String getUsuario() {
        return usuario;
    }

    /**
     * Establece el nombre de usuario o correo.
     *
     * @param usuario nombre o email del usuario
     */
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    /**
     * Obtiene la contraseña del usuario (sin cifrar).
     *
     * @return contraseña en texto plano
     */
    public String getPassword() {
        return password;
    }

    /**
     * Establece la contraseña del usuario.
     *
     * @param password contraseña a establecer
     */
    public void setPassword(String password) {
        this.password = password;
    }
}