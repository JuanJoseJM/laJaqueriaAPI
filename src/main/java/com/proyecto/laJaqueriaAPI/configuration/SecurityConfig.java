package com.proyecto.laJaqueriaAPI.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Configuración principal de seguridad del sistema.
 * <p>
 * Define políticas de acceso a endpoints, gestión de login, logout
 * y uso de cifrado para contraseñas.
 */
@Configuration
@EnableMethodSecurity
public class SecurityConfig {

    /**
     * Define la cadena de filtros de seguridad.
     * <p>
     * - Desactiva CSRF para compatibilidad con API REST.
     * - Permite el acceso libre a "/login" y "/register".
     * - Requiere autenticación para cualquier otro endpoint.
     * - Habilita login con formulario HTML.
     *
     * @param http objeto de configuración HTTP de Spring Security
     * @return configuración construida de la cadena de filtros
     * @throws Exception si ocurre un error durante la configuración
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // Desactiva CSRF para APIs
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/usuarios/login").permitAll() // ermite login personalizado
                        .anyRequest().authenticated()
                )
                .sessionManagement(session -> session
                        .sessionCreationPolicy(org.springframework.security.config.http.SessionCreationPolicy.STATELESS) // Sin sesiones
                )
                .formLogin(form -> form.disable()) // Desactiva login HTML clásico
                .httpBasic(basic -> basic.disable()); // Desactiva HTTP Basic

        return http.build();
    }

    /**
     * Proporciona un codificador de contraseñas usando BCrypt.
     *
     * @return una instancia de PasswordEncoder
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}