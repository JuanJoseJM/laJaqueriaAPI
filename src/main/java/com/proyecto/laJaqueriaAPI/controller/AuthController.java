package com.proyecto.laJaqueriaAPI.controller;

import com.proyecto.laJaqueriaAPI.dao.UsuarioRepository;
import com.proyecto.laJaqueriaAPI.dto.UsuarioDTO;
import com.proyecto.laJaqueriaAPI.model.Rol;
import com.proyecto.laJaqueriaAPI.model.Usuario;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

/**
 * Controlador que gestiona el registro de nuevos usuarios.
 * <p>
 * Este endpoint permite que cualquier usuario se registre
 * automáticamente con el rol SOCIO por defecto.
 */
@RestController
@RequestMapping
public class AuthController {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    /**
     * Constructor que inyecta el repositorio de usuarios y el codificador de contraseñas.
     *
     * @param usuarioRepository repositorio de acceso a datos de Usuario
     * @param passwordEncoder   codificador de contraseñas seguro
     */
    public AuthController(UsuarioRepository usuarioRepository,
                          PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Endpoint de registro para nuevos usuarios.
     * <p>
     * Se registran como SOCIO por defecto. Verifica que el correo no esté ya registrado.
     *
     * @param dto objeto con los datos del usuario (nombre, apellidos, email, contraseña)
     * @return respuesta HTTP con mensaje de éxito o conflicto
     */
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody UsuarioDTO dto) {
        if (usuarioRepository.findByEmail(dto.getEmail()).isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("El correo ya está registrado");
        }

        Usuario usuario = new Usuario();
        usuario.setNombre(dto.getNombre());
        usuario.setApellidos(dto.getApellidos());
        usuario.setEmail(dto.getEmail());
        usuario.setPassword(passwordEncoder.encode(dto.getPassword()));
        usuario.setRol(Rol.SOCIO); // por defecto

        usuarioRepository.save(usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body("Usuario registrado correctamente");
    }
}