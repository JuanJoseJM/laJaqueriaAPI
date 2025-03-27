package com.proyecto.laJaqueriaAPI.model;


import jakarta.persistence.*;

@Entity
@Table(name="Usuarios")
public class Usuario {

    private String nombre = "";
    private String apellidos = "";
    private String email = "";
    private String password = "";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) private Long idUsuario;

    public Usuario() {
    }

    public Usuario(String nombre, String apellidos, String email, String password, Long idUsuario) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.email = email;
        this.password = password;
        this.idUsuario = idUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }
}
