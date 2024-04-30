package com.sapi0.dilaxiabackend.data.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.sql.Date;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserUpdateDTO {

    @JsonProperty("name")
    public String name;
    @JsonProperty("surname")
    public String surname;
    @JsonProperty("type")
    public int type;

    public UserUpdateDTO(String name, String surname, int type) {
        this.name = name;
        this.surname = surname;
        this.type = type;
    }

    public UserUpdateDTO() {
    }
}
