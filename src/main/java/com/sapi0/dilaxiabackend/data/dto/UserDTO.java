package com.sapi0.dilaxiabackend.data.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.sql.Date;

public class UserDTO {

    @JsonProperty("name")
    public String name;
    @JsonProperty("surname")
    public String surname;
    @JsonProperty("email")
    public String email;
    @JsonProperty("type")
    public int type;

    public UserDTO(String name, String surname, String email, int type) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.type = type;
    }

    public UserDTO() {
    }
}
