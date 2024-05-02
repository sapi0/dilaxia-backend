package com.sapi0.dilaxiabackend.data.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class EventLightDTO {

    @JsonProperty("id")
    public int id;
    @JsonProperty("title")
    public String title;
    @JsonProperty("capacity")
    public int capacity;

    public EventLightDTO(int id, String title, int capacity) {
        this.id = id;
        this.title = title;
        this.capacity = capacity;
    }

    public EventLightDTO() {
    }
}
