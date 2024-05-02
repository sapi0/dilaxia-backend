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

    public UserUpdateDTO(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    public UserUpdateDTO() {
    }
}
