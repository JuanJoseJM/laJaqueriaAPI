package com.proyecto.laJaqueriaAPI.services;

import com.proyecto.laJaqueriaAPI.dao.SocioRepository;
import com.proyecto.laJaqueriaAPI.model.Socio;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.List;

@Component
public class SocioService {
    private SocioRepository repository;

    public SocioService(SocioRepository repository) {
        this.repository = repository;
    }

    public List<Socio> getAllSocios(String nombre) {

        if (StringUtils.hasText(nombre)) {
            return this.repository.findByNombreStartsWith(nombre);
        } else {
            return this.repository.findAll();
        }

    }

    public Socio createSocio(String nombre, String apellidos, int edad) {

        Socio socio = new Socio();
        socio.setNombre(nombre);
        socio.setApellidos(apellidos);
        socio.setEdad(edad);
        return this.repository.save(socio);
    }

    public Socio getSocioById(Long id) {

        return this.repository.findById(id).get();
    }

    public Socio updateSocio(String nombre, String apellidos, int edad, Long id) {

        Socio socio = repository.findById(id).get();
        socio.setNombre(nombre);
        socio.setApellidos(apellidos);
        socio.setEdad(edad);
        socio.setIdSocio(id);
        return this.repository.save(socio);
    }

    public void deleteSocio(Long id) {

        this.repository.deleteById(id);
    }
}
