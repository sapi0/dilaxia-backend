package com.sapi0.dilaxiabackend.data.dao;

import com.sapi0.dilaxiabackend.data.entity.User;

import java.sql.SQLException;
import java.util.List;

public interface IUserDao {

    int count() throws SQLException;
    List<User> research(int page, int pageSize) throws SQLException;
    User get(int id) throws SQLException;
    User get(String email) throws SQLException;
    void add(User user) throws SQLException;
    void update(int id, User user) throws SQLException;
    void delete(int id) throws SQLException;

}
