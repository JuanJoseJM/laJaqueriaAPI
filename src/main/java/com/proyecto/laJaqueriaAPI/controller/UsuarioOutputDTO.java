package com.proyecto.laJaqueriaAPI.controller;

public class UsuarioOutputDTO {

    private String nombre = "";
    private String apellidos = "";
    private String email = "";
    private Long idUser;

    public UsuarioOutputDTO(String nombre, String apellidos, String email, Long idUser) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.email = email;
        this.idUser = idUser;
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

    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }
}
