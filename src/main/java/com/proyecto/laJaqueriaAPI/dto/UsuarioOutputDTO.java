package com.proyecto.laJaqueriaAPI.dto;

public class UsuarioOutputDTO {
    private String nombre;
    private String apellidos;
    private String email;
    private Long id;

    public UsuarioOutputDTO(String nombre, String apellidos, String email, Long id) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.email = email;
        this.id = id;
    }

    public String getNombre() { return nombre; }
    public String getApellidos() { return apellidos; }
    public String getEmail() { return email; }
    public Long getId() { return id; }
}
