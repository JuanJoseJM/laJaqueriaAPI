package com.proyecto.laJaqueriaAPI.model;

import jakarta.persistence.*;

@Entity
public class Socio {
    private String nombre = "";
    private String apellidos = "";
    private int edad;

    @OneToOne
    @JoinColumn(name = "idSocio")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idSocio;

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

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public Long getIdSocio() {
        return idSocio;
    }

    public void setIdSocio(Long idSocio) {
        this.idSocio = idSocio;
    }
}
