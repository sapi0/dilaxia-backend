package com.sapi0.dilaxiabackend.data.entity;

import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.sql.LocalDateTime;

public class Subscription {

    private Integer id;
    private LocalDateTime timestamp;
    private Event event;
    private User user;

    public Subscription(Integer id, LocalDateTime timestamp, Event event, User user){
        this.id = id;
        this.timestamp = timestamp;
        this.event = event;
        this.user = user;
    }

    public Integer getId() {return id;}h

    public void setId(Integer id) { this.id = id; }

    public LocalDateTime getTimestamp() {return timestamp;}

    public void setTimestamp(LocalDateTime timestamp) { this.timestamp = timestamp; }

    public Event getEvent() {return event;}

    public void setEvent(Event event) { this.event = event; }

    public User getUser() {return user;}

    public void setUser(User user) { this.user = user; }
}
