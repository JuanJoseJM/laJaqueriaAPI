package com.proyecto.laJaqueriaAPI.dao;

import com.proyecto.laJaqueriaAPI.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

/**
 * The interface Usuario repository.
 */
@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    /**
     * Find by email optional.
     *
     * @param email the email
     * @return the optional
     */
    Optional<Usuario> findByEmail(String email); // metodo necesario para Spring Security

    /**
     * Find by email and password optional.
     *
     * @param email    the email
     * @param password the password
     * @return the optional
     */
    Optional<Usuario> findByEmailAndPassword(String email, String password); // este lo puedes mantener si lo necesitas
}
