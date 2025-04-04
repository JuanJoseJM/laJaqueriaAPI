package com.proyecto.laJaqueriaAPI.controller;

import com.proyecto.laJaqueriaAPI.model.Socio;
import com.proyecto.laJaqueriaAPI.services.SocioService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/socios")
public class SocioController {

    private final SocioService service;

    public SocioController(SocioService service) {
        this.service = service;
    }

    // Consulta de socios (accesible por todos los socios)
    @CrossOrigin
    @GetMapping
    public List<Socio> getSocios(@RequestParam(required = false) String nombre) {
        return service.getAllSocios(nombre);
    }

    // Consulta de un socio por su ID (accesible por todos los socios)
    @CrossOrigin
    @GetMapping("/{id}")
    public Socio getSocioById(@PathVariable Long id) {
        return service.getSocioById(id);
    }

    // Alta de un nuevo socio (solo administradores)
    @PreAuthorize("hasRole('ADMIN')")
    @CrossOrigin
    @PostMapping
    public Socio crearSocio(@RequestBody SocioDTO socioDTO) {
        return service.createSocio(socioDTO.getNombre(), socioDTO.getApellidos(), socioDTO.getEdad());
    }

    // Baja de un socio (solo administradores)
    @PreAuthorize("hasRole('ADMIN')")
    @CrossOrigin
    @DeleteMapping("/{id}")
    public void deleteSocio(@PathVariable Long id) {
        service.deleteSocio(id);
    }

    // Modificación de un socio (accesible por todos los socios)
    @CrossOrigin
    @PutMapping("/{id}")
    public Socio updateSocio(@PathVariable Long id, @RequestBody SocioDTO socioDTO) {
        return service.updateSocio(socioDTO.getNombre(), socioDTO.getApellidos(), socioDTO.getEdad(), id);
    }

    // Generación de informe PDF (solo administradores)
    @PreAuthorize("hasRole('ADMIN')")
    @CrossOrigin
    @GetMapping("/informe-pdf")
    public void generarInformePDF() {
        service.generarInformePDF();
    }
}