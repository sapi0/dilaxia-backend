package com.sapi0.dilaxiabackend.data.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.sql.Date;

public class UserDTO {

    @JsonProperty("id")
    public int id;
    @JsonProperty("name")
    public String name;
    @JsonProperty("surname")
    public String surname;
    @JsonProperty("type")
    public int type;

    public UserDTO(int id, String name, String surname, int type) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.type = type;
    }

    public UserDTO() {
    }
}
