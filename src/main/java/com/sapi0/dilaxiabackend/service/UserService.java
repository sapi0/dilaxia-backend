package com.sapi0.dilaxiabackend.service;

import com.sapi0.dilaxiabackend.data.DaoFactory;
import com.sapi0.dilaxiabackend.data.dto.UserDTO;
import com.sapi0.dilaxiabackend.data.dto.UserMeDTO;
import com.sapi0.dilaxiabackend.data.entity.User;
import com.sapi0.dilaxiabackend.data.impl.UserDaoImpl;

import java.sql.SQLException;

public class UserService {

    private UserDaoImpl dao;

    public UserService() {
        dao = DaoFactory.instance.getUserDao();
    }

    public UserDTO getUserByID(int userID) throws SQLException {
        User user = dao.get(userID);
        UserDTO dto = new UserDTO(user.getName(), user.getSurname(), user.getType());
        return dto;
    }

    public UserMeDTO getUserMe(int userID) throws SQLException {
        User user = dao.get(userID);
        UserMeDTO dto = new UserMeDTO(user.getName(), user.getSurname(), user.getType(), user.getEmail());
        return dto;
    }


}
