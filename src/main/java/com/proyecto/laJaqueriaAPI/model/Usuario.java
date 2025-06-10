package com.proyecto.laJaqueriaAPI.model;

import jakarta.persistence.*;

/**
 * Entidad que representa un usuario del sistema.
 * <p>
 * Un usuario puede tener rol de ADMIN o SOCIO y se autentica por email y contraseña.
 */
@Entity
@Table(name = "usuarios")
public class Usuario {

    /** Clave primaria del usuario (mapeada como id_usuario en la base de datos) */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private Long idUsuario;

    /** Nombre del usuario */
    private String nombre;

    /** Apellidos del usuario */
    private String apellidos;

    /** Correo electrónico único del usuario */
    private String email;

    /** Contraseña encriptada */
    private String password;

    /** Rol del usuario: ADMIN o SOCIO */
    @Enumerated(EnumType.STRING)
    private Rol rol;

    /**
     * Constructor vacío requerido por JPA
     */
    public Usuario() {}

    /**
     * Constructor completo.
     *
     * @param nombre    nombre del usuario
     * @param apellidos apellidos del usuario
     * @param email     correo electrónico
     * @param password  contraseña
     * @param rol       rol asignado al usuario
     */
    public Usuario(String nombre, String apellidos, String email, String password, Rol rol) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.email = email;
        this.password = password;
        this.rol = rol;
    }

    /**
     * Gets id usuario.
     *
     * @return the id usuario
     */
    public Long getIdUsuario() {
        return idUsuario;
    }

    /**
     * Sets id usuario.
     *
     * @param idUsuario the id usuario
     */
    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    /**
     * Gets nombre.
     *
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Sets nombre.
     *
     * @param nombre the nombre
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Gets apellidos.
     *
     * @return the apellidos
     */
    public String getApellidos() {
        return apellidos;
    }

    /**
     * Sets apellidos.
     *
     * @param apellidos the apellidos
     */
    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    /**
     * Gets email.
     *
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets email.
     *
     * @param email the email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets password.
     *
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets password.
     *
     * @param password the password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Gets rol.
     *
     * @return the rol
     */
    public Rol getRol() {
        return rol;
    }

    /**
     * Sets rol.
     *
     * @param rol the rol
     */
    public void setRol(Rol rol) {
        this.rol = rol;
    }
}