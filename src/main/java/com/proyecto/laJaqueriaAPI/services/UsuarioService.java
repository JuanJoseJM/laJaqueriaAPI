package com.proyecto.laJaqueriaAPI.services;

import com.proyecto.laJaqueriaAPI.dao.UsuarioRepository;
import com.proyecto.laJaqueriaAPI.model.Usuario;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.HexFormat;
import java.util.List;
import java.util.Optional;

public class UsuarioService {

    private UsuarioRepository repository;

    public UsuarioService(UsuarioRepository repository) {

        this.repository = repository;
    }

    public List<Usuario> getAllUsuarios(){

        return this.repository.findAll();
    }

    public Usuario getUsuarioById(Long idUser){

        return this.repository.findById(idUser).get();
    }

    public LoginOutput login(String email, String password) throws NoSuchAlgorithmException {

        Optional<Usuario> usuario = this.repository.findByEmailAndPassword(email, generatePassword(password));
        String accesscode = null;
        if (usuario.isPresent()){
            accesscode = Base64.getEncoder().encodeToString("userUser:sociosJaqueria".getBytes());
        }
        return new LoginOutput(accesscode, email);
    }

    private String generatePassword(String password) throws NoSuchAlgorithmException {

        final MessageDigest digest = MessageDigest.getInstance("SHA3-256");
        final byte[] hashbytes = digest.digest(
                password.getBytes(StandardCharsets.UTF_8));
        String sha3Hex = HexFormat.of().formatHex(hashbytes);

        return sha3Hex;
    }

    public Usuario createUsuario(String nombre, String apellidos, String email, String password) throws NoSuchAlgorithmException {

        Usuario usuario = new Usuario();
        usuario.setNombre(nombre);
        usuario.setApellidos(apellidos);
        usuario.setEmail(email);
        usuario.setPassword(generatePassword(password));
        return this.repository.save(usuario);
    }

    public Usuario updateUsuario(String nombre, String apellidos, String email, String password, Long idUsuario){

        try {
            Usuario usuario = repository.findById(idUsuario).get();
            usuario.setNombre(nombre);
            usuario.setApellidos(apellidos);
            usuario.setEmail(email);
            usuario.setPassword(generatePassword(password));
            usuario.setIdUsuario(idUsuario);
            return this.repository.save(usuario);

        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }

    }

    public void deleteUser(Long idUser){

        this.repository.deleteById(idUser);
    }
}
