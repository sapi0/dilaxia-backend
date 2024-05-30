package com.sapi0.dilaxiabackend.data.entity;

import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.sql.Timestamp;

public class Subscription {

    private Integer id;
    private Timestamp timestamp;
    private Event event;
    private User user;
    
//costruttori
    public Subscription(Integer id, Timestamp timestamp, Event event, User user){
        this.id = id;
        this.timestamp = timestamp;
        this.event = event;
        this.user = user;
    }

//getter and setter
    public Integer getId() {return id;}

    public void setId(Integer id) { this.id = id; }

    public Timestamp getTimestamp() {return timestamp;}

    public void setTimestamp(Timestamp timestamp) { this.timestamp = timestamp; }

    public Event getEvent() {return event;}

    public void setEvent(Event event) { this.event = event; }

    public User getUser() {return user;}

    public void setUser(User user) { this.user = user; }
}
