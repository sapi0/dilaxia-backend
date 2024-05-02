package com.sapi0.dilaxiabackend.data.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sapi0.dilaxiabackend.data.entity.User;

import java.sql.Timestamp;

public class EventUserDTO {

    @JsonProperty("id")
    public int id;
    @JsonProperty("name")
    public String name;
    @JsonProperty("surname")
    public String surname;


    public EventUserDTO(int id, String name, String surname) {
        this.id = id;
        this.name = name;
        this.surname = surname;
    }


    public EventUserDTO() {

    }

    public EventUserDTO(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.surname = user.getSurname();
    }

}
