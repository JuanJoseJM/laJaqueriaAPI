package com.proyecto.laJaqueriaAPI.controller;

import com.proyecto.laJaqueriaAPI.dto.LoginDTO;
import com.proyecto.laJaqueriaAPI.dto.UsuarioDTO;
import com.proyecto.laJaqueriaAPI.dto.UsuarioOutputDTO;
import com.proyecto.laJaqueriaAPI.model.Usuario;
import com.proyecto.laJaqueriaAPI.services.LoginOutput;
import com.proyecto.laJaqueriaAPI.services.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Controlador REST que gestiona las operaciones sobre usuarios:
 * registro, consulta, modificación y eliminación.
 * También incluye el endpoint de login personalizado.
 */
@RestController
@RequestMapping("/usuarios")
@CrossOrigin
public class UsuarioController {

    private final UsuarioService service;

    /**
     * Constructor que inyecta el servicio de usuarios.
     * @param service instancia del servicio
     */
    public UsuarioController(UsuarioService service) {
        this.service = service;
    }

    /**
     * Convierte una entidad Usuario en un DTO de salida.
     */
    private UsuarioOutputDTO convertir(Usuario usuario) {
        return new UsuarioOutputDTO(
                usuario.getNombre(),
                usuario.getApellidos(),
                usuario.getEmail(),
                usuario.getIdUsuario()
        );
    }

    /**
     * Convierte una lista de entidades Usuario en una lista de DTOs.
     */
    private List<UsuarioOutputDTO> convertirLista(List<Usuario> usuarioList) {
        return usuarioList.stream().map(this::convertir).collect(Collectors.toList());
    }

    /**
     * Obtiene todos los usuarios registrados.
     * @return lista de usuarios
     */
    @PreAuthorize("hasRole('SOCIO') or hasRole('ADMIN')")
    @GetMapping
    public List<UsuarioOutputDTO> getUsuarios() {
        return convertirLista(this.service.getAllUsuarios());
    }

    /**
     * Consulta un usuario por su ID.
     * @param idUsuario ID del usuario
     * @return usuario encontrado o 404 si no existe
     */
    @PreAuthorize("hasRole('SOCIO') or hasRole('ADMIN')")
    @GetMapping("/{idUsuario}")
    public ResponseEntity<UsuarioOutputDTO> getUsuarioById(@PathVariable Long idUsuario) {
        Optional<Usuario> usuario = Optional.ofNullable(service.getUsuarioById(idUsuario));
        return usuario.map(value -> ResponseEntity.ok(convertir(value)))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    /**
     * Endpoint de login personalizado. Devuelve token de acceso y email.
     * @param loginDTO credenciales de login
     * @return token JWT y email del usuario
     */
    @PostMapping("/login")
    public ResponseEntity<LoginOutput> loginUsuario(@RequestBody LoginDTO loginDTO) throws NoSuchAlgorithmException {
        LoginOutput usuario = this.service.login(loginDTO.getUsuario(), loginDTO.getPassword());
        return ResponseEntity.ok(new LoginOutput(usuario.getAccesscode(), usuario.getEmail()));
    }

    /**
     * Crea un nuevo usuario. Solo accesible por administradores.
     * @param usuarioDTO datos del nuevo usuario
     * @return DTO del usuario creado
     */
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<UsuarioOutputDTO> crearUsuario(@RequestBody UsuarioDTO usuarioDTO) throws NoSuchAlgorithmException {
        Usuario nuevoUsuario = this.service.createUsuario(
                usuarioDTO.getNombre(), usuarioDTO.getApellidos(), usuarioDTO.getEmail(), usuarioDTO.getPassword());
        return ResponseEntity.status(HttpStatus.CREATED).body(convertir(nuevoUsuario));
    }

    /**
     * Actualiza los datos de un usuario existente.
     * @param idUsuario ID del usuario
     * @param usuarioDTO nuevos datos
     * @return usuario actualizado
     */
    @PreAuthorize("hasRole('SOCIO') or hasRole('ADMIN')")
    @PutMapping("/{idUsuario}")
    public ResponseEntity<UsuarioOutputDTO> updateUsuario(@PathVariable Long idUsuario, @RequestBody UsuarioDTO usuarioDTO) {
        Usuario usuarioActualizado = this.service.updateUsuario(
                usuarioDTO.getNombre(), usuarioDTO.getApellidos(), usuarioDTO.getEmail(), usuarioDTO.getPassword(), idUsuario);
        return ResponseEntity.ok(convertir(usuarioActualizado));
    }

    /**
     * Elimina un usuario por su ID. Solo permitido para administradores.
     * @param idUsuario ID del usuario
     * @return respuesta sin contenido
     */
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{idUsuario}")
    public ResponseEntity<Void> deleteUsuario(@PathVariable Long idUsuario) {
        service.deleteUser(idUsuario);
        return ResponseEntity.noContent().build();
    }
}