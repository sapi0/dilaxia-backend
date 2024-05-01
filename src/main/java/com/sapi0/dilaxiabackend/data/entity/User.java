package com.sapi0.dilaxiabackend.data.entity;

import java.sql.Timestamp;

public class User {

    private Integer id;
    private String name;
    private String surname;
    private String email;
    private String hash;
    private Integer type;
    private Timestamp created;

    public User(Integer id, String name, String surname, String email, String hash, Integer type, Timestamp created) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.hash = hash;
        this.type = type;
        this.created = created;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Timestamp getCreated() {return created;}

    public void setCreated(Timestamp created) {this.created = created;}
}
