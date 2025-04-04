package com.proyecto.laJaqueriaAPI.services;

import com.proyecto.laJaqueriaAPI.dao.UsuarioRepository;
import com.proyecto.laJaqueriaAPI.model.Usuario;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {
    private final UsuarioRepository repository;
    private final PasswordEncoder passwordEncoder;

    public UsuarioService(UsuarioRepository repository) {
        this.repository = repository;
        this.passwordEncoder = new BCryptPasswordEncoder(); // Usa BCrypt para encriptar contraseñas
    }

    /**
     * Obtiene la lista de todos los usuarios.
     */
    public List<Usuario> getAllUsuarios() {
        return repository.findAll();
    }

    /**
     * Obtiene un usuario por su ID, lanzando una excepción si no existe.
     */
    public Usuario getUsuarioById(Long idUser) {
        return repository.findById(idUser)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
    }

    /**
     * Inicia sesión y genera un código de acceso si las credenciales son correctas.
     */
    public LoginOutput login(String email, String password) {
        Usuario usuario = repository.findByEmailAndPassword(email)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        if (!passwordEncoder.matches(password, usuario.getPassword())) {
            throw new RuntimeException("Contraseña incorrecta");
        }

        // Genera un token de autenticación en lugar de usar Base64 (por seguridad)
        String accesscode = generateJWTToken(email);
        return new LoginOutput(accesscode, email);
    }

    /**
     * Crea un nuevo usuario con contraseña encriptada.
     */
    public Usuario createUsuario(String nombre, String apellidos, String email, String password) {
        if (repository.findByEmailAndPassword(email).isPresent()) {
            throw new RuntimeException("El correo ya está registrado");
        }

        Usuario usuario = new Usuario();
        usuario.setNombre(nombre);
        usuario.setApellidos(apellidos);
        usuario.setEmail(email);
        usuario.setPassword(passwordEncoder.encode(password)); // Encripta la contraseña con BCrypt
        return repository.save(usuario);
    }

    /**
     * Actualiza los datos de un usuario existente.
     */
    public Usuario updateUsuario(String nombre, String apellidos, String email, String password, Long idUsuario) {
        Usuario usuario = repository.findById(idUsuario)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        usuario.setNombre(nombre);
        usuario.setApellidos(apellidos);
        usuario.setEmail(email);

        if (password != null && !password.isEmpty()) {
            usuario.setPassword(passwordEncoder.encode(password)); // Solo actualiza la contraseña si es necesario
        }

        return repository.save(usuario);
    }

    /**
     * Elimina un usuario por ID.
     */
    public void deleteUser(Long idUser) {
        if (!repository.existsById(idUser)) {
            throw new RuntimeException("Usuario no encontrado");
        }
        repository.deleteById(idUser);
    }

    /**
     * Método simulado para generar un token JWT.
     */
    private String generateJWTToken(String email) {
        return "token-falso-para-" + email; // Aquí deberías implementar JWT real
    }
}