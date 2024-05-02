package com.sapi0.dilaxiabackend.data.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sapi0.dilaxiabackend.data.entity.User;

import java.sql.Time;
import java.sql.Timestamp;

public class EventUserDTO {

    @JsonProperty("id")
    public int id;
    @JsonProperty("name")
    public String name;
    @JsonProperty("surname")
    public String surname;
    @JsonProperty("timestamp")
    public Timestamp timestamp;

    public EventUserDTO(int id, String name, String surname, Timestamp timestamp) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.timestamp = timestamp;
    }


    public EventUserDTO() {

    }

}
