package com.sapi0.dilaxiabackend.data.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.sql.Timestamp;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class EventUpdateDTO {

    @JsonProperty("title")
    public String title;
    @JsonProperty("description")
    public String description;
    @JsonProperty("start")
    public Timestamp start;
    @JsonProperty("end")
    public Timestamp end;
    @JsonProperty("subscription_limit")
    public Timestamp subscription_limit;
    @JsonProperty("capacity")
    public Integer capacity;
    @JsonProperty("place")
    public String place;
    @JsonProperty("public")
    public Boolean _public;


    public EventUpdateDTO(String title, String description, Timestamp start, Timestamp end, Timestamp subscription_limit, Integer capacity, String place, Boolean _public) {
        this.title = title;
        this.description = description;
        this.start = start;
        this.end = end;
        this.subscription_limit = subscription_limit;
        this.capacity = capacity;
        this.place = place;
        this._public = _public;
    }


    public EventUpdateDTO() {
    }
}
