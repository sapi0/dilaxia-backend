package com.sapi0.dilaxiabackend.data.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sapi0.dilaxiabackend.data.entity.User;

import java.sql.Timestamp;

public class EventUserDTO {


    @JsonProperty("name")
    public String name;
    @JsonProperty("surname")
    public String surname;
    @JsonProperty("email")
    public String email;


    public EventUserDTO(String name, String surname, String email) {
        this.name = name;
        this.surname = surname;
        this.email = email;
    }


    public EventUserDTO() {

    }

    public EventUserDTO(User user){
        this.name = user.name;
        this.surname = user.surname;
        this.email = user.email;
    }

}
