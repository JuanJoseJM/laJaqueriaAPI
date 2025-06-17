package com.proyecto.laJaqueriaAPI.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.config.http.SessionCreationPolicy;

/**
 * Configuración principal de seguridad del sistema.
 * <p>
 * Define políticas de acceso a endpoints, gestión de login, logout
 * y uso de cifrado para contraseñas.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    /**
     * Define la cadena de filtros de seguridad.
     * <p>
     * - Desactiva CSRF para compatibilidad con API REST.
     * - Permite el acceso libre a "/usuarios/login".
     * - Requiere autenticación para cualquier otro endpoint.
     * - Usa política sin estado para evitar sesiones HTTP.
     * - No se usa formulario web ni autenticación básica en este caso.
     *
     * @param http objeto de configuración HTTP de Spring Security
     * @return configuración construida de la cadena de filtros
     * @throws Exception si ocurre un error durante la configuración
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(new AntPathRequestMatcher("/usuarios/login")).permitAll()
                        .anyRequest().authenticated()
                )
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                );

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