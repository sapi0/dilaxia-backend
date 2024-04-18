package com.sapi0.dilaxiabackend.data.entity;

import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.sql.Timestamp;

public class Subscription {

    private int id;
    private Timestamp timestamp;
    private int event;
    private int user;

    public Subscription(int id, Timestamp timestamp, int event, int user){
        this.id = id;
        this.timestamp = timestamp;
        this.event = event;
        this.user = user;
    }

    public int getId() {return id;}

    public void setId(int id) { this.id = id; }

    public Timestamp getTimestamp() {return timestamp;}

    public void setTimestamp(Timestamp timestamp) { this.timestamp = timestamp; }

    public int getEvent() {return event;}

    public void setEvent(int event) { this.event = event; }

    public int getUser() {return user;}

    public void setUser(int user) { this.user = user; }
}
