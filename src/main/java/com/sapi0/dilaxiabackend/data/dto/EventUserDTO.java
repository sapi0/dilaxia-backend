package com.sapi0.dilaxiabackend.data.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sapi0.dilaxiabackend.data.entity.User;

import java.sql.Timestamp;

public class EventUserDTO {


    @JsonProperty("name")
    public String name;
    @JsonProperty("surname")
    public String surname;


    public EventUserDTO(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }


    public EventUserDTO() {

    }

    public EventUserDTO(User user){
        this.name = user.getName();
        this.surname = user.getSurname();
    }

}
