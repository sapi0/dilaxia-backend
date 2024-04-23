package com.sapi0.dilaxiabackend.data.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.sql.Timestamp;

public class EventDTO {


    @JsonProperty("id")
    public int id;
    @JsonProperty("title")
    public String title;
    @JsonProperty("description")
    public String description;
    @JsonProperty("created")
    public Timestamp created;
    @JsonProperty("edited")
    public Timestamp edited;
    @JsonProperty("start")
    public Timestamp start;
    @JsonProperty("end")
    public Timestamp end;
    @JsonProperty("subscription_limit")
    public Timestamp subscription_limit;
    @JsonProperty("capacity")
    public int capacity;
    @JsonProperty("place")
    public String place;
    @JsonProperty("type")
    public int type;    // TODO: forse questo diventa una stringa per leggibilita' da parte dell'utilizzatore dell'api
    @JsonProperty("creator")
    public UserDTO creator;
    @JsonProperty("public")
    public boolean _public;


    public EventDTO(int id, String title, String description, Timestamp created, Timestamp edited, Timestamp start, Timestamp end, Timestamp subscription_limit, int capacity, String place, int type, UserDTO creator, Boolean _public) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.created = created;
        this.edited = edited;
        this.start = start;
        this.end = end;
        this.subscription_limit = subscription_limit;
        this.capacity = capacity;
        this.place = place;
        this.type = type;
        this.creator = creator;
        this._public = _public;
    }

    public EventDTO() {
    }
}
