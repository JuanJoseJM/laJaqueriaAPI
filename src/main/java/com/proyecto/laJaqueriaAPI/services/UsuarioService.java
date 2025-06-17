package com.proyecto.laJaqueriaAPI.services;

import com.proyecto.laJaqueriaAPI.dao.UsuarioRepository;
import com.proyecto.laJaqueriaAPI.model.Usuario;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Servicio encargado de gestionar la lógica de negocio relacionada con los usuarios.
 * <p>
 * Incluye autenticación, creación, modificación y eliminación de usuarios.
 */
@Service
public class UsuarioService {

    private final UsuarioRepository repository;
    private final PasswordEncoder passwordEncoder;

    /**
     * Constructor que inyecta el repositorio y configura el codificador de contraseñas.
     *
     * @param repository repositorio de acceso a datos de usuario
     */
    public UsuarioService(UsuarioRepository repository) {
        this.repository = repository;
        this.passwordEncoder = new BCryptPasswordEncoder(); // Usa BCrypt para encriptar contraseñas
    }

    /**
     * Obtiene la lista de todos los usuarios registrados.
     *
     * @return lista de usuarios
     */
    public List<Usuario> getAllUsuarios() {
        return repository.findAll();
    }

    /**
     * Obtiene un usuario por su ID.
     *
     * @param idUser identificador del usuario
     * @return objeto Usuario o lanza excepción si no existe
     */
    public Usuario getUsuarioById(Long idUser) {
        return repository.findById(idUser)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
    }

    /**
     * Autentica un usuario usando email y contraseña.
     *
     * @param email    correo electrónico
     * @param password contraseña en texto plano
     * @return objeto LoginOutput con token y correo
     */
    public LoginOutput login(String email, String password) {
        System.out.println("Intentando login con: " + email);

        Usuario usuario = repository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        System.out.println("Comparando con hash: " + usuario.getPassword());

        if (!passwordEncoder.matches(password, usuario.getPassword())) {
            System.out.println("Contraseña incorrecta");
            throw new RuntimeException("Contraseña incorrecta");
        }

        String accesscode = generateJWTToken(email);
        System.out.println("Login correcto: " + email);
        return new LoginOutput(accesscode, email);
    }


    /**
     * Crea un nuevo usuario si no existe previamente.
     *
     * @param nombre    nombre
     * @param apellidos apellidos
     * @param email     correo
     * @param password  contraseña
     * @return usuario creado
     */
    public Usuario createUsuario(String nombre, String apellidos, String email, String password) {
        if (repository.findByEmailAndPassword(email, password).isPresent()) {
            throw new RuntimeException("El correo ya está registrado");
        }

        Usuario usuario = new Usuario();
        usuario.setNombre(nombre);
        usuario.setApellidos(apellidos);
        usuario.setEmail(email);
        usuario.setPassword(passwordEncoder.encode(password));
        return repository.save(usuario);
    }

    /**
     * Actualiza los datos de un usuario existente.
     *
     * @param nombre    nuevo nombre
     * @param apellidos nuevos apellidos
     * @param email     nuevo correo
     * @param password  nueva contraseña
     * @param idUsuario ID del usuario a modificar
     * @return usuario actualizado
     */
    public Usuario updateUsuario(String nombre, String apellidos, String email, String password, Long idUsuario) {
        Usuario usuario = repository.findById(idUsuario)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        usuario.setNombre(nombre);
        usuario.setApellidos(apellidos);
        usuario.setEmail(email);

        if (password != null && !password.isEmpty()) {
            usuario.setPassword(passwordEncoder.encode(password));
        }

        return repository.save(usuario);
    }

    /**
     * Elimina un usuario por su ID.
     *
     * @param idUser ID del usuario a eliminar
     */
    public void deleteUser(Long idUser) {
        if (!repository.existsById(idUser)) {
            throw new RuntimeException("Usuario no encontrado");
        }
        repository.deleteById(idUser);
    }

    /**
     * Método simulado para generar un token JWT.
     * @param email correo electrónico del usuario
     * @return token ficticio
     */
    private String generateJWTToken(String email) {
        return "token-falso-para-" + email; // Aquí deberías implementar JWT real
    }
}