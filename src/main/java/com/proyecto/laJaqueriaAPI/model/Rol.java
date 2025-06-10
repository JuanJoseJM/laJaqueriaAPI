package com.proyecto.laJaqueriaAPI.model;

/**
 * Enumeración que representa los roles posibles dentro del sistema.
 * <p>
 * - ADMIN: tiene acceso completo al sistema, incluyendo gestión y domótica.
 * - SOCIO: acceso limitado a funcionalidades básicas (eventos, perfil, cuotas).
 */
public enum Rol {
    /**
     * Admin rol.
     */
    ADMIN,
    /**
     * Socio rol.
     */
    SOCIO;
}