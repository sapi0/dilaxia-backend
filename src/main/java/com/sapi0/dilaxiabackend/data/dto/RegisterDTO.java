package com.sapi0.dilaxiabackend.data.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RegisterDTO {

    @JsonProperty("name")
    public String name;

    @JsonProperty("surname")
    public String surname;

    @JsonProperty("email")
    public String email;

    @JsonProperty("password")
    public String password;

    public RegisterDTO(String name, String surname, String email, String password) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
    }

    public RegisterDTO() {}

}
