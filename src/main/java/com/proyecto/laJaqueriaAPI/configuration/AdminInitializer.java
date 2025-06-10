package com.proyecto.laJaqueriaAPI.configuration;

import com.proyecto.laJaqueriaAPI.dao.UsuarioRepository;
import com.proyecto.laJaqueriaAPI.model.Rol;
import com.proyecto.laJaqueriaAPI.model.Usuario;
import jakarta.annotation.PostConstruct;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * Clase que se ejecuta automáticamente al iniciar la aplicación para insertar
 * usuarios predeterminados (admin y socio) si no existen en la base de datos.
 * <p>
 * Esta clase garantiza que siempre habrá al menos un usuario administrador y
 * un socio de prueba disponibles para autenticación inicial.
 */
@Component
public class AdminInitializer {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    /**
     * Constructor que inyecta los componentes necesarios.
     *
     * @param usuarioRepository Repositorio de usuarios
     * @param passwordEncoder   Codificador de contraseñas
     */
    public AdminInitializer(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Método que se ejecuta automáticamente después de inicializar el componente.
     * Crea un usuario administrador y uno socio si no existen en la base de datos.
     */
    @PostConstruct
    public void init() {
        // Admin
        if (usuarioRepository.findByEmail("admin@ejemplo.com").isEmpty()) {
            Usuario admin = new Usuario();
            admin.setNombre("admin");
            admin.setApellidos("admin");
            admin.setEmail("admin@ejemplo.com");
            admin.setPassword(passwordEncoder.encode("admin123"));
            admin.setRol(Rol.ADMIN);
            usuarioRepository.save(admin);
        }

        // Socio
        if (usuarioRepository.findByEmail("socio@ejemplo.com").isEmpty()) {
            Usuario socio = new Usuario();
            socio.setNombre("juan");
            socio.setApellidos("juarez");
            socio.setEmail("socio@ejemplo.com");
            socio.setPassword(passwordEncoder.encode("socio123"));
            socio.setRol(Rol.SOCIO);
            usuarioRepository.save(socio);
        }
    }
}