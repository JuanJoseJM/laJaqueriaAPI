package com.proyecto.laJaqueriaAPI.configuration;

import com.proyecto.laJaqueriaAPI.dao.UsuarioRepository;
import com.proyecto.laJaqueriaAPI.model.Usuario;
import com.proyecto.laJaqueriaAPI.model.Rol;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Optional;

/**
 * Inicializador de datos que se ejecuta al iniciar la aplicación.
 * Se asegura de que exista un usuario administrador con credenciales válidas.
 */
@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initAdminUser(UsuarioRepository usuarioRepository) {
        return args -> {
            String emailAdmin = "admin@ejemplo.com";
            String passwordPlano = "admin123";

            // Comprobamos si el usuario ya existe
            Optional<Usuario> adminExistente = usuarioRepository.findByEmail(emailAdmin);

            if (adminExistente.isPresent()) {
                Usuario admin = adminExistente.get();
                BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

                // Solo actualizamos si la contraseña aún no está cifrada
                if (!admin.getPassword().startsWith("$2a$")) {
                    admin.setPassword(encoder.encode(passwordPlano));
                    usuarioRepository.save(admin);
                    System.out.println("Contraseña de admin actualizada correctamente.");
                } else {
                    System.out.println("ℹUsuario admin ya tiene contraseña cifrada.");
                }
            } else {
                // Crear nuevo usuario admin si no existe
                Usuario nuevoAdmin = new Usuario();
                nuevoAdmin.setEmail(emailAdmin);
                nuevoAdmin.setPassword(new BCryptPasswordEncoder().encode(passwordPlano));
                nuevoAdmin.setRol(Rol.ADMIN); // Asegúrate de que este enum exista
                usuarioRepository.save(nuevoAdmin);
                System.out.println("Usuario admin creado correctamente.");
            }
        };
    }
}
