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
 *
 * Define políticas de acceso a endpoints, gestión de login, logout
 * y uso de cifrado para contraseñas.
 */
@Configuration
@EnableMethodSecurity
public class SecurityConfig {

    /**
     * Define la cadena de filtros de seguridad.
     *
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
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/login", "/register").permitAll()
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .loginProcessingUrl("/login")
                        .permitAll()
                )
                .logout(logout -> logout.permitAll());

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