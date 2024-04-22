package com.sapi0.dilaxiabackend.data.entity;

import java.sql.Timestamp;

public class Event {
    private int id;
    private String title;
    private String description;
    private Timestamp created;
    private Timestamp edited;
    private Timestamp start;
    private Timestamp end;
    private Timestamp subscription_limit;
    private int capacity;
    private String place;
    private int type;
    private int creator;
    private int _public;



    public Event(int id, String title, String description, Timestamp created, Timestamp edited, Timestamp start, Timestamp end, Timestamp subscription_limit, int capacity, String place,int type, int creator, int _public ){
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

    public int getId() {return id;}
    public void setId(int id) {this.id = id;}

    public String getTitle() {return title;}
    public void setTitle(String title) {this.title = title;}

    public String getDescription() {return description;}
    public void setDescription(String description) {this.description = description;}

    public Timestamp getCreated() {return created;}
    public void setCreated(Timestamp created) {this.created = created;}

    public Timestamp getEdited() {return edited;}
    public void setEdited(Timestamp edited) {this.edited = edited;}

    public Timestamp getStart() {return start;}
    public void setStart(Timestamp start) {this.start = start;}

    public Timestamp getEnd() {return end;}
    public void setEnd(Timestamp end) {this.end = end;}

    public Timestamp getSubscriptionLimit() {return subscription_limit;}
    public void setSubscriptionLimit(Timestamp limit) {this.subscription_limit = limit;}

    public int getCapacity() {return capacity;}
    public void setCapacity(int capacity) {this.capacity = capacity;}

    public String getPlace() {return place;}
    public void setPlace(String place) {this.place = place;}

    public int getType() {return type;}
    public void setType(int type) {this.type = type;}

    public int getCreator() {return creator;}
    public void setCreator(int creator) {this.creator = creator;}

    public int get_public() {return _public;}
    public void set_public(int _public) {this._public = _public;}









}