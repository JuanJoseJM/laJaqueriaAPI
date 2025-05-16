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

@RestController
@RequestMapping("/usuarios")
@CrossOrigin
public class UsuarioController {
    private final UsuarioService service;

    public UsuarioController(UsuarioService service) {
        this.service = service;
    }

    private UsuarioOutputDTO convertir(Usuario usuario) {
        return new UsuarioOutputDTO(
                usuario.getNombre(),
                usuario.getApellidos(),
                usuario.getEmail(),
                usuario.getId()
        );
    }

    private List<UsuarioOutputDTO> convertirLista(List<Usuario> usuarioList) {
        return usuarioList.stream().map(this::convertir).collect(Collectors.toList());
    }

    @PreAuthorize("hasRole('SOCIO') or hasRole('ADMIN')")
    @GetMapping
    public List<UsuarioOutputDTO> getUsuarios() {
        return convertirLista(this.service.getAllUsuarios());
    }

    @PreAuthorize("hasRole('SOCIO') or hasRole('ADMIN')")
    @GetMapping("/{idUsuario}")
    public ResponseEntity<UsuarioOutputDTO> getUsuarioById(@PathVariable Long idUsuario) {
        Optional<Usuario> usuario = Optional.ofNullable(service.getUsuarioById(idUsuario));
        return usuario.map(value -> ResponseEntity.ok(convertir(value)))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PostMapping("/login")
    public ResponseEntity<LoginOutput> loginUsuario(@RequestBody LoginDTO loginDTO) throws NoSuchAlgorithmException {
        LoginOutput usuario = this.service.login(loginDTO.getUsuario(), loginDTO.getPassword());
        return ResponseEntity.ok(new LoginOutput(usuario.getAccesscode(), usuario.getEmail()));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<UsuarioOutputDTO> crearUsuario(@RequestBody UsuarioDTO usuarioDTO) throws NoSuchAlgorithmException {
        Usuario nuevoUsuario = this.service.createUsuario(
                usuarioDTO.getNombre(), usuarioDTO.getApellidos(), usuarioDTO.getEmail(), usuarioDTO.getPassword());
        return ResponseEntity.status(HttpStatus.CREATED).body(convertir(nuevoUsuario));
    }

    @PreAuthorize("hasRole('SOCIO') or hasRole('ADMIN')")
    @PutMapping("/{idUsuario}")
    public ResponseEntity<UsuarioOutputDTO> updateUsuario(@PathVariable Long idUsuario, @RequestBody UsuarioDTO usuarioDTO) {
        Usuario usuarioActualizado = this.service.updateUsuario(
                usuarioDTO.getNombre(), usuarioDTO.getApellidos(), usuarioDTO.getEmail(), usuarioDTO.getPassword(), idUsuario);
        return ResponseEntity.ok(convertir(usuarioActualizado));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{idUsuario}")
    public ResponseEntity<Void> deleteUsuario(@PathVariable Long idUsuario) {
        service.deleteUser(idUsuario);
        return ResponseEntity.noContent().build();
    }
}
