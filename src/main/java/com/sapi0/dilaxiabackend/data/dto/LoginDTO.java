package com.sapi0.dilaxiabackend.data.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LoginDTO {

    @JsonProperty("email")
    public String email;
    @JsonProperty("password")
    public String password;

    public LoginDTO(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public LoginDTO(){}

}
