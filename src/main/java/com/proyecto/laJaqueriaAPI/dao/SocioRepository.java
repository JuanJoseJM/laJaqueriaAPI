package com.proyecto.laJaqueriaAPI.dao;

import com.proyecto.laJaqueriaAPI.model.Socio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface SocioRepository extends JpaRepository<Socio, Long> {

    List<Socio> findByNombreStartsWith(String nombre);
}