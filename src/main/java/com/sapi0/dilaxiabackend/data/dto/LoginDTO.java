package com.sapi0.dilaxiabackend.data.dto;

public class LoginDTO {

    public String email;
    public String password;

    public LoginDTO(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
