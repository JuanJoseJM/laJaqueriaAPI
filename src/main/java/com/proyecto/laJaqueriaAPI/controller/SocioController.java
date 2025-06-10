package com.proyecto.laJaqueriaAPI.controller;

import com.proyecto.laJaqueriaAPI.dto.SocioDTO;
import com.proyecto.laJaqueriaAPI.model.Socio;
import com.proyecto.laJaqueriaAPI.services.SocioService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador REST que gestiona operaciones relacionadas con los socios del sistema.
 * <p>
 * Permite listar, registrar, actualizar y eliminar socios, así como generar informes.
 */
@RestController
@RequestMapping("/socios")
public class SocioController {

    private final SocioService service;

    /**
     * Constructor que inyecta el servicio de socios.
     *
     * @param service servicio de lógica de negocio para socios
     */
    public SocioController(SocioService service) {
        this.service = service;
    }

    /**
     * Obtiene una lista de todos los socios registrados, con opción de filtrar por nombre.
     *
     * @param nombre (opcional) nombre para filtrar
     * @return lista de socios
     */
    @CrossOrigin
    @GetMapping
    public List<Socio> getSocios(@RequestParam(required = false) String nombre) {
        return service.getAllSocios(nombre);
    }

    /**
     * Consulta los datos de un socio por su ID.
     *
     * @param id identificador del socio
     * @return objeto Socio encontrado
     */
    @CrossOrigin
    @GetMapping("/{id}")
    public Socio getSocioById(@PathVariable Long id) {
        return service.getSocioById(id);
    }

    /**
     * Crea un nuevo socio. Solo permitido para administradores.
     *
     * @param socioDTO datos del socio a registrar
     * @return socio creado
     */
    @PreAuthorize("hasRole('ADMIN')")
    @CrossOrigin
    @PostMapping
    public Socio crearSocio(@RequestBody SocioDTO socioDTO) {
        return service.createSocio(socioDTO.getNombre(), socioDTO.getApellidos(), socioDTO.getEdad());
    }

    /**
     * Elimina un socio existente. Solo permitido para administradores.
     *
     * @param id identificador del socio
     */
    @PreAuthorize("hasRole('ADMIN')")
    @CrossOrigin
    @DeleteMapping("/{id}")
    public void deleteSocio(@PathVariable Long id) {
        service.deleteSocio(id);
    }

    /**
     * Actualiza los datos de un socio existente.
     *
     * @param id       identificador del socio
     * @param socioDTO nuevos datos del socio
     * @return socio actualizado
     */
    @CrossOrigin
    @PutMapping("/{id}")
    public Socio updateSocio(@PathVariable Long id, @RequestBody SocioDTO socioDTO) {
        return service.updateSocio(socioDTO.getNombre(), socioDTO.getApellidos(), socioDTO.getEdad(), id);
    }

    /**
     * Genera un informe PDF con la información de los socios.
     * Solo accesible por administradores.
     */
    @PreAuthorize("hasRole('ADMIN')")
    @CrossOrigin
    @GetMapping("/informe-pdf")
    public void generarInformePDF() {
        service.generarInformePDF();
    }
}