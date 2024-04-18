package com.sapi0.dilaxiabackend.data.entity;

import java.sql.Timestamp;

public class User {

    public int id;
    public String name;
    public String surname;
    public String email;
    public String hash;
    public int type;
    public Timestamp created;

    public User(int id, String name, String surname, String email, String hash, int type, Timestamp created) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.hash = hash;
        this.type = type;
        this.created = created;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Timestamp getCreated() {return created;}

    public void setCreated(Timestamp created) {this.created = created;}
}
