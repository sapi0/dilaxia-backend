package com.sapi0.dilaxiabackend.data.impl;

import com.sapi0.dilaxiabackend.data.dao.IEventDao;
import com.sapi0.dilaxiabackend.data.entity.Event;
import com.sapi0.dilaxiabackend.data.entity.User;

import javax.naming.NamingException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EventDaoImpl extends DaoImpl implements IEventDao {

    private static final String TABLE_NAME = "event";
    private static final String TABLE_USER = "user";
    private static final String TABLE_SUBS = "Subscription";

    private PreparedStatement getAllEvent, getDailyEvents, getEventByTitle, addEvent, updateEventById, deleteEventById, getSubcribeById, getAllSubscribe;

    public EventDaoImpl() throws NamingException, SQLException {
        super();    // necessario
        initStatements();
    }



    public void initStatements() throws SQLException {
        getAllEvent = conn.prepareStatement("SELECT * FROM " + TABLE_NAME);
        getDailyEvents = conn.prepareStatement("SELECT * FROM " + TABLE_NAME + " WHERE date = ?");
        getEventByTitle = conn.prepareStatement("SELECT * FROM " + TABLE_NAME + " WHERE id = ?");
        addEvent = conn.prepareStatement("INSERT INTO " + TABLE_NAME + "(title, description, start, end, subscription_limit, capacity, place, creator, type, public) VALUES(?,?,?,?,?,?,?,?,?,?) ");
        updateEventById = conn.prepareStatement("UPDATE " + TABLE_NAME + " SET date = ? WHERE id = ?");
        deleteEventById = conn.prepareStatement("DELETE FROM " + TABLE_NAME + " WHERE id = ?");
        getSubcribeById = conn.prepareStatement("SELECT * FROM " + TABLE_NAME + " WHERE id = ?");
        getAllSubscribe = conn.prepareStatement("SELECT * FROM "+ TABLE_SUBS + " INNER JOIN " + TABLE_NAME + "ON " + TABLE_SUBS + ".event = " + TABLE_NAME+".id INNER JOIN "+ TABLE_USER+ " ON "+TABLE_SUBS+".user = "+TABLE_USER+".id WHERE "+TABLE_SUBS+".event = ?;");

    }

    private Event createEventFromResultSet(ResultSet rs) throws SQLException {
        int _id = rs.getInt(1);
        String title = rs.getString(2);
        String description = rs.getString(3);
        Timestamp created = rs.getTimestamp(4);
        Timestamp edited = rs.getTimestamp(5);
        Timestamp start = rs.getTimestamp(6);
        Timestamp end = rs.getTimestamp(7);
        Timestamp subscrption_limit = rs.getTimestamp(8);
        int capacity = rs.getInt(9);
        String place = rs.getString(10);
        int type = rs.getInt(11);
        int creator_id = rs.getInt(12);
        boolean _public = rs.getBoolean(13);

        return new Event(_id,title, description, created, edited, start, end, subscrption_limit, capacity, place, type, creator_id, _public );
    }

    // TODO
    public List<User> getSubscribedUsers(int id) throws SQLException{
        getAllSubscribe.setInt(1,id);
        ResultSet rs = getAllSubscribe.executeQuery();
        List<User> subs = new ArrayList<>();


       /* while(rs.next()){
            User us = new User(rs.getInt(1));
            subs.add(us);
        }*/
        return subs;
    }

    public List<Event> dailyAll(Date data) throws SQLException{
        ResultSet rs = getDailyEvents.executeQuery();
        List<Event> event = new ArrayList<>();

        while(rs.next()){
            event.add(createEventFromResultSet(rs));
        }
        return event;
    }
    public List<Event> all() throws SQLException{
        ResultSet rs = getAllEvent.executeQuery();
        List<Event> events = new ArrayList<>();

        while(rs.next()){
            events.add(createEventFromResultSet(rs));
        }
        return events;
    }
    public Event get(int id) throws SQLException{
        getEventByTitle.setInt(1, id);
        ResultSet rs = getEventByTitle.executeQuery();

        if(rs.next())
            return createEventFromResultSet(rs);
        return null;
    }
    public Event get(String title) throws SQLException{
        getEventByTitle.setString(1, title);
        ResultSet rs = getEventByTitle.executeQuery();

        if(rs.next()){
            return createEventFromResultSet(rs);
        }
        return null;
    }
    public void add(Event event) throws SQLException{
        addEvent.setString(1, event.getTitle());
        addEvent.setString(2, event.getDescription());
        addEvent.setTimestamp(3, event.getStart());
        addEvent.setTimestamp(4, event.getEnd());
        addEvent.setTimestamp(5, event.getSubscriptionLimit());
        addEvent.setInt(6, event.getCapacity());
        addEvent.setString(7, event.getPlace());
        addEvent.setInt(8, event.getType());
        addEvent.setBoolean(9, event.get_public());


        addEvent.execute();
    }
    public void update(int id, Event event){

    }
    public void delete(int id){

    }
    public void subscribe(int id){

    }
    public void unsubscribe(int id){

    }
    @Override
    protected void close() throws SQLException {
        getAllEvent.close();
        getDailyEvents.close();
        getEventByTitle.close();
        addEvent.close();
        updateEventById.close();
        deleteEventById.close();
        getSubcribeById.close();
    }
}
