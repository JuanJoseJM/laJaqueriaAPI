package com.proyecto.laJaqueriaAPI.configuration;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Clase de configuración de seguridad para definir autenticación básica
 * y un usuario de prueba en memoria.
 *
 * Esta configuración es utilizada principalmente para pruebas o como
 * fallback cuando no se usa autenticación con base de datos.
 */
@AutoConfiguration
@EnableWebSecurity
public class Configuration {

    /**
     * Define un usuario en memoria para autenticación.
     *
     * @param passwordEncoder el codificador de contraseñas inyectado
     * @return un gestor de usuarios con un solo usuario definido
     */
    @Bean
    public InMemoryUserDetailsManager usuarioDetailsService(PasswordEncoder passwordEncoder) {
        UserDetails usuario = User.withUsername("userUser")
                .password(passwordEncoder.encode("sociosJaqueria"))
                .roles("USUARIO")
                .build();
        return new InMemoryUserDetailsManager(usuario);
    }

    /**
     * Configura la cadena de filtros de seguridad (Security Filter Chain).
     *
     * Desactiva CSRF, permite acceso libre al login y exige autenticación
     * para el resto de endpoints.
     *
     * @param http el objeto HttpSecurity
     * @return la cadena de filtros configurada
     * @throws Exception en caso de error de configuración
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable);
        return http.authorizeHttpRequests(request -> request
                        .requestMatchers("/login**").permitAll()
                        .anyRequest().authenticated())
                .httpBasic(Customizer.withDefaults())
                .build();
    }

    /**
     * Provee un codificador de contraseñas compatible con múltiples algoritmos.
     *
     * @return un PasswordEncoder con delegación automática
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}