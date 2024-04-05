package com.sapi0.dilaxiabackend.data.dto;

import java.sql.Date;

public class UserDTO {

    public String name;
    public String surname;
    public String email;
    public String password;
    public Date birth;
    public int type;

    public UserDTO(String name, String surname, String email, String password, Date birth, int type) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.birth = birth;
        this.type = type;
    }
}
