package com.proyecto.laJaqueriaAPI.controller;

public class LoginOutput {
    String accesscode;
    String email;

    public LoginOutput(String accesscode, String email) {

        this.accesscode = accesscode;
        this.email = email;
    }

    public String getAccesscode() {
        return accesscode;
    }

    public void setAccesscode(String accesscode) {
        this.accesscode = accesscode;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

