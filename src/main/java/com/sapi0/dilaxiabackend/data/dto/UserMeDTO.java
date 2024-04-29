package com.sapi0.dilaxiabackend.data.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.sql.Date;

public class UserMeDTO {


    @JsonProperty("name")
    public String name;
    @JsonProperty("surname")
    public String surname;
    @JsonProperty("type")
    public int type;
    @JsonProperty("email")
    public String email;


    public UserMeDTO(String name, String surname, int type, String email) {
        this.name = name;
        this.surname = surname;
        this.type = type;
        this.email = email;
    }

    public UserMeDTO() {
    }
}
