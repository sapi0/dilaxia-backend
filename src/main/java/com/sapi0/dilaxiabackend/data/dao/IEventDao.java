package com.sapi0.dilaxiabackend.data.dao;

import com.sapi0.dilaxiabackend.data.entity.Event;
import com.sapi0.dilaxiabackend.data.entity.Subscription;
import com.sapi0.dilaxiabackend.data.entity.User;
import org.joda.time.DateTime;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

public interface IEventDao {

    int count() throws SQLException;
    List<Event> research(int page, int pageSize) throws SQLException;
    List<Event> research(String query, int page, int pageSize) throws SQLException;
    List<Event> research(DateTime date, int page, int pageSize) throws SQLException;
    Event get(int id) throws SQLException;
    void add(Event event) throws SQLException;
    void update(int id, Event event) throws SQLException;
    void delete(int id) throws SQLException;

}
