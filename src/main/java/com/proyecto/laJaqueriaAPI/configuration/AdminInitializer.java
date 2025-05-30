package com.proyecto.laJaqueriaAPI.configuration;

import com.proyecto.laJaqueriaAPI.dao.UsuarioRepository;
import com.proyecto.laJaqueriaAPI.model.Rol;
import com.proyecto.laJaqueriaAPI.model.Usuario;
import jakarta.annotation.PostConstruct;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class AdminInitializer {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public AdminInitializer(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostConstruct
    public void init() {
        //Admin
        if (usuarioRepository.findByEmail("admin@example.com").isEmpty()) {
            Usuario admin = new Usuario();
            admin.setNombre("Admin");
            admin.setApellidos("Root");
            admin.setEmail("admin@example.com");
            admin.setPassword(passwordEncoder.encode("admin123"));
            admin.setRol(Rol.ADMIN);
            usuarioRepository.save(admin);
        }

        //Socio
        if (usuarioRepository.findByEmail("socio@example.com").isEmpty()) {
            Usuario socio = new Usuario();
            socio.setNombre("Juan");
            socio.setApellidos("Juarez");
            socio.setEmail("socio@example.com");
            socio.setPassword(passwordEncoder.encode("socio123"));
            socio.setRol(Rol.SOCIO);
            usuarioRepository.save(socio);
        }
    }
}