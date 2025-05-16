package com.proyecto.laJaqueriaAPI.dto;

public class LoginResponse {
    private String accesscode;
    private String email;

    public LoginResponse(String accesscode, String email) {
        this.accesscode = accesscode;
        this.email = email;
    }

    public String getAccesscode() {
        return accesscode;
    }

    public String getEmail() {
        return email;
    }
}
