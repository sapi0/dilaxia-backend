package com.sapi0.dilaxiabackend.data.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.sql.Date;

public class UserUpdateDTO {

    @JsonProperty("name")
    public String name;
    @JsonProperty("surname")
    public String surname;
    @JsonProperty("birth")
    public Date birth;
    @JsonProperty("type")
    public int type;

    public UserUpdateDTO(String name, String surname, Date birth, int type) {
        this.name = name;
        this.surname = surname;
        this.birth = birth;
        this.type = type;
    }

    public UserUpdateDTO() {
    }
}
