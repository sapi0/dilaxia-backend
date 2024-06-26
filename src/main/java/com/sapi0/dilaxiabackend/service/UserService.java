package com.sapi0.dilaxiabackend.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sapi0.dilaxiabackend.data.DaoFactory;
import com.sapi0.dilaxiabackend.data.dto.*;
import com.sapi0.dilaxiabackend.data.entity.Event;
import com.sapi0.dilaxiabackend.data.entity.User;
import com.sapi0.dilaxiabackend.data.impl.UserDaoImpl;
import com.sapi0.dilaxiabackend.data.Mapper;

import java.sql.SQLException;
import java.util.List;

public class UserService {

    private UserDaoImpl dao;

    public UserService() {
        dao = DaoFactory.instance.getUserDao();
    }

    public UserDTO getUserByID(int userID) throws SQLException {
        User user = dao.get(userID);
        UserDTO dto = new UserDTO(user.getId(), user.getName(), user.getSurname(), user.getType());
        return dto;
    }

    public UserMeDTO getUserMe(int userID) throws SQLException {
        User user = dao.get(userID);
        UserMeDTO dto = new UserMeDTO(user.getId(), user.getName(), user.getSurname(), user.getType(), user.getEmail());
        return dto;
    }

    public UserListDTO getUserList(int pageSize, int page) throws SQLException {
        int totalSize = dao.count();
        List<User> result = dao.research(pageSize, page);

        UserListDTO dto = new UserListDTO();
        dto.pageCount = pageSize;
        dto.page = page;
        dto.totalSize = totalSize;

        for(User u : result) {
            dto.data.add(new UserDTO(u.getId(), u.getName(), u.getSurname(), u.getType()));
        }

        return dto;
    }

    public UserMeDTO editUserMe(UserUpdateDTO in, int id) throws SQLException, JsonProcessingException {
        User user = dao.get(id);

        User updated = Mapper.update(user, in);

        dao.update(id, updated);

        UserMeDTO dto = new UserMeDTO(updated.getId(), updated.getName(), updated.getSurname(), updated.getType(), updated.getEmail());

        return dto;
    }

    public void deleteUser(int id) throws SQLException {
        dao.delete(id);
    }
}
