package com.proyecto.laJaqueriaAPI.model;

/**
 * Enumeración que representa los roles posibles dentro del sistema.
 *
 * - ADMIN: tiene acceso completo al sistema, incluyendo gestión y domótica.
 * - SOCIO: acceso limitado a funcionalidades básicas (eventos, perfil, cuotas).
 */
public enum Rol {
    ADMIN,
    SOCIO;
}