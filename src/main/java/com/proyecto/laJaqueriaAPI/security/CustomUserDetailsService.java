package com.proyecto.laJaqueriaAPI.security;

import com.proyecto.laJaqueriaAPI.dao.UsuarioRepository;
import com.proyecto.laJaqueriaAPI.model.Usuario;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

import java.util.Collections;

/**
 * Servicio personalizado que implementa UserDetailsService.
 * <p>
 * Carga los detalles del usuario desde la base de datos usando su email.
 * Es usado por Spring Security para realizar el proceso de autenticación.
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;

    /**
     * Constructor que inyecta el repositorio de usuarios.
     *
     * @param usuarioRepository repositorio de acceso a datos de Usuario
     */
    public CustomUserDetailsService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    /**
     * Carga un usuario por su nombre de usuario (email).
     *
     * @param email correo electrónico del usuario
     * @return UserDetails con sus credenciales y roles
     * @throws UsernameNotFoundException si el usuario no existe
     */
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Usuario user = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));

        return new User(
                user.getEmail(),
                user.getPassword(),
                Collections.singleton(new SimpleGrantedAuthority("ROLE_" + user.getRol().name()))
        );
    }
}