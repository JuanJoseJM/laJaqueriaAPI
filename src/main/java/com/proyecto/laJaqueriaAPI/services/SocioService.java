package com.proyecto.laJaqueriaAPI.services;

import com.proyecto.laJaqueriaAPI.dao.SocioRepository;
import com.proyecto.laJaqueriaAPI.model.Socio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Servicio que gestiona la lógica de negocio relacionada con los socios.
 */
@Service
public class SocioService {

    private final SocioRepository repository;

    /**
     * Constructor que inyecta el repositorio de socios.
     *
     * @param repository repositorio de acceso a datos de Socio
     */
    @Autowired
    public SocioService(SocioRepository repository) {
        this.repository = repository;
    }

    /**
     * Obtiene una lista de todos los socios, o filtrados por nombre si se indica.
     *
     * @param nombre nombre parcial para filtrar (puede ser null)
     * @return lista de socios
     */
    public List<Socio> getAllSocios(String nombre) {
        if (nombre != null) {
            return repository.findByNombreContaining(nombre); // Filtra por nombre si se proporciona
        }
        return repository.findAll(); // Si no, devuelve todos los socios
    }

    /**
     * Busca un socio por su ID.
     *
     * @param id identificador del socio
     * @return socio encontrado o null si no existe
     */
    public Socio getSocioById(Long id) {
        return repository.findById(id).orElse(null); // Retorna el socio con el ID dado
    }

    /**
     * Crea un nuevo socio con los datos proporcionados.
     *
     * @param nombre    nombre del socio
     * @param apellidos apellidos del socio
     * @param edad      edad del socio
     * @return socio creado
     */
    public Socio createSocio(String nombre, String apellidos, int edad) {
        Socio socio = new Socio(nombre, apellidos, edad);
        return repository.save(socio); // Crea un nuevo socio
    }

    /**
     * Actualiza los datos de un socio existente.
     *
     * @param nombre    nuevo nombre
     * @param apellidos nuevos apellidos
     * @param edad      nueva edad
     * @param id        identificador del socio a actualizar
     * @return socio actualizado o null si no existe
     */
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

    /**
     * Elimina un socio por su ID.
     *
     * @param id identificador del socio a eliminar
     */
    public void deleteSocio(Long id) {
        repository.deleteById(id); // Elimina el socio con el ID dado
    }

    /**
     * Genera un informe PDF con la información de los socios.
     * (Actualmente sin implementación real).
     */
    public void generarInformePDF() {
        // Implementación para generar un informe PDF de todos los socios
    }
}