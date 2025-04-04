package com.proyecto.laJaqueriaAPI.services;

import com.proyecto.laJaqueriaAPI.dao.SocioRepository;
import com.proyecto.laJaqueriaAPI.model.Socio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SocioService {

    private final SocioRepository repository;

    @Autowired
    public SocioService(SocioRepository repository) {
        this.repository = repository;
    }

    public List<Socio> getAllSocios(String nombre) {
        if (nombre != null) {
            return repository.findByNombreContaining(nombre); // Filtra por nombre si se proporciona
        }
        return repository.findAll(); // Si no, devuelve todos los socios
    }

    public Socio getSocioById(Long id) {
        return repository.findById(id).orElse(null); // Retorna el socio con el ID dado
    }

    public Socio createSocio(String nombre, String apellidos, int edad) {
        Socio socio = new Socio(nombre, apellidos, edad);
        return repository.save(socio); // Crea un nuevo socio
    }

    public Socio updateSocio(String nombre, String apellidos, int edad, Long id) {
        Socio socio = repository.findById(id).orElse(null);
        if (socio != null) {
            socio.setNombre(nombre);
            socio.setApellidos(apellidos);
            socio.setEdad(edad);
            return repository.save(socio); // Actualiza los detalles del socio
        }
        return null;
    }

    public void deleteSocio(Long id) {
        repository.deleteById(id); // Elimina el socio con el ID dado
    }

    public void generarInformePDF() {
        // Implementación para generar un informe PDF de todos los socios
        // Puedes usar bibliotecas como iText o Apache PDFBox
    }
}
