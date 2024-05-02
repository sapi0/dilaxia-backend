package com.sapi0.dilaxiabackend.data.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.sql.Date;

public class UserMeDTO {

    @JsonProperty("id")
    public int id;
    @JsonProperty("name")
    public String name;
    @JsonProperty("surname")
    public String surname;
    @JsonProperty("type")
    public Integer type;
    @JsonProperty("email")
    public String email;

    public UserMeDTO(int id, String name, String surname, Integer type, String email) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.type = type;
        this.email = email;
    }

    public UserMeDTO() {
    }
}
