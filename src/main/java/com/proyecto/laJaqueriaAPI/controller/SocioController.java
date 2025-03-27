package com.proyecto.laJaqueriaAPI.controller;

import com.proyecto.laJaqueriaAPI.model.Socio;
import com.proyecto.laJaqueriaAPI.services.SocioService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SocioController {

    private SocioService service;
    public SocioController(SocioService service) {
        this.service = service;
    }

    @CrossOrigin
    @GetMapping("/socios")
    public List<Socio> getSocios(@RequestParam(required = false) String nombre) {
        return this.service.getAllSocios(nombre);
    }

    @CrossOrigin
    @PostMapping("/socios")
    public Socio crearSocio(@RequestBody SocioDTO socioDTO) {
        return this.service.createSocio(socioDTO.getNombre(), socioDTO.getApellidos(), socioDTO.getEdad());
    }

    @CrossOrigin
    @GetMapping("/socios/{id}")
    public Socio getSocioById(@PathVariable Long id) {
        return this.service.getSocioById(id);
    }

    @CrossOrigin
    @PutMapping("/socios/{id}")
    public Socio updateSocio(@PathVariable Long id, @RequestBody SocioDTO socioDTO) {
        return this.service.updateSocio(socioDTO.getNombre(), socioDTO.getApellidos(), socioDTO.getEdad(), id);
    }

    @CrossOrigin
    @DeleteMapping("/socios/{id}")
    public void deleteSocio(@PathVariable Long id) {
        this.service.deleteSocio(id);
    }

}
