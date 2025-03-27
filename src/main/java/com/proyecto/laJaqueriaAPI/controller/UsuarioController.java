package com.proyecto.laJaqueriaAPI.controller;

import com.proyecto.laJaqueriaAPI.model.Usuario;
import com.proyecto.laJaqueriaAPI.services.LoginOutput;
import com.proyecto.laJaqueriaAPI.services.UsuarioService;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

@RestController
public class UsuarioController {
    private UsuarioService service;

    public UsuarioController(UsuarioService service) {
        this.service = service;
    }

    private UsuarioOutputDTO convertir(Usuario usuario){
        UsuarioOutputDTO usuarioOutputDTO = new UsuarioOutputDTO(usuario.getNombre(), usuario.getApellidos(), usuario.getEmail(), usuario.getIdUsuario());
        return usuarioOutputDTO;
    }

    private List<UsuarioOutputDTO> convertirLista(List<Usuario> usuarioList){
        List<UsuarioOutputDTO> usuarioOutputDTOList = new ArrayList<>();
        for (int i = 0; i<usuarioList.size(); i++){
            usuarioOutputDTOList.add(convertir(usuarioList.get(i)));
        }
        return usuarioOutputDTOList;
    }

    @CrossOrigin
    @GetMapping("/usuarios")
    public List<UsuarioOutputDTO> getUsuarios(){
        return convertirLista(this.service.getAllUsuarios());
    }

    @CrossOrigin
    @GetMapping("/usuarios/{idUsuario}")
    public UsuarioOutputDTO getUsuarioById(@PathVariable Long idUsuario){
        return convertir(this.service.getUsuarioById(idUsuario));
    }

    @CrossOrigin
    @PostMapping("/login")
    public LoginOutput loginUsuario(@RequestBody LoginDTO loginDTO) throws NoSuchAlgorithmException {
        return new LoginOutput(this.service.login(loginDTO.getUsuario(), loginDTO.getPassword()).getAccesscode(), this.service.login(loginDTO.getUsuario(), loginDTO.getPassword()).getEmail());
    }

    @CrossOrigin
    @PostMapping("/usuarios")
    public UsuarioOutputDTO crearUsuario(@RequestBody UsuarioDTO usuarioDTO) throws NoSuchAlgorithmException {
        return convertir(this.service.createUsuario(
                usuarioDTO.getNombre(), usuarioDTO.getApellidos(), usuarioDTO.getEmail(), usuarioDTO.getPassword()));
    }

    @CrossOrigin
    @PutMapping("/usuarios/{idUsuario}")
    public UsuarioOutputDTO updateUsuario(@PathVariable Long idUsuario, @RequestBody UsuarioDTO usuarioDTO){
        return convertir(this.service.updateUsuario(
                usuarioDTO.getNombre(), usuarioDTO.getApellidos(), usuarioDTO.getEmail(), usuarioDTO.getPassword(), idUsuario));
    }

    @CrossOrigin
    @DeleteMapping("/usuarios/{idUsario}")
    public void deleteUsuario(@PathVariable Long idUsuario){
        this.service.deleteUser(idUsuario);
    }
}
