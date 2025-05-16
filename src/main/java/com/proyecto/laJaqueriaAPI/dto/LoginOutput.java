package com.proyecto.laJaqueriaAPI.dto;

public class LoginOutput {
    private String accesscode;
    private String email;

    public LoginOutput(String accesscode, String email) {
        this.accesscode = accesscode;
        this.email = email;
    }

    public String getAccesscode() { return accesscode; }
    public String getEmail() { return email; }
}

