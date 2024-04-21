package com.sapi0.dilaxiabackend.data.dao;

import com.sapi0.dilaxiabackend.data.entity.Event;
import com.sapi0.dilaxiabackend.data.entity.User;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

public interface IEventDao {

    List<User> getSubscribedUsers(int id) throws SQLException;
    List<Event> dailyAll(Date data) throws SQLException;
    List<Event> all() throws SQLException;
    Event get(int id) throws SQLException;
    void get(String title) throws SQLException;
    void add(Event event) throws SQLException;
    void update(int id, Event event);
    void delete(int id);
    void subscribe(int id);
    void unsubscribe(int id);
}
