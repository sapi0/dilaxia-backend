package com.sapi0.dilaxiabackend.data.impl;

import com.sapi0.dilaxiabackend.data.dao.IEventDao;
import com.sapi0.dilaxiabackend.data.entity.Event;
import com.sapi0.dilaxiabackend.data.entity.Subscription;
import com.sapi0.dilaxiabackend.data.entity.User;
import com.sapi0.dilaxiabackend.utils.NullableResultSet;
import org.checkerframework.checker.units.qual.N;
import org.joda.time.DateTime;

import javax.naming.NamingException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EventDaoImpl extends DaoImpl implements IEventDao {

    private static final String TABLE_NAME = "event";
    private static final String TABLE_USER = "user";
    private static final String TABLE_SUBS = "subscription";

    private PreparedStatement count, getEventByID, listSubcribedByEventId, addEvent, updateEventById, deleteEventById, neutralResearch, dailyResearch, fullTextResearch;

    public EventDaoImpl() throws NamingException, SQLException {
        super();    // necessario
    }

    public void initStatements() throws SQLException {
        count = conn.prepareStatement("SELECT COUNT(id) FROM " + TABLE_NAME);

        getEventByID = conn.prepareStatement(
                "SELECT " +
                    "tEvent.id, tEvent.title, tEvent.description, tEvent.created, tEvent.edited, tEvent.start, tEvent.end, tEvent.subscription_limit, tEvent.capacity, tEvent.place, tEvent.public, " +
                    "tUser.id userID, tUser.name, tUser.surname, tUser.type userType " +
                    "FROM " + TABLE_NAME + " tEvent INNER JOIN " + TABLE_USER + " tUser ON tEvent.creator = tUser.id " +
                    "WHERE tEvent.id = ?"
        );

        listSubcribedByEventId = conn.prepareStatement(
                "SELECT " +
                    "tSubs.id, tSubs.timestamp tEvent.id eventID, tEvent.title, tEvent.capacity, tUser.id userID, tUser.name, tUser.surname " +
                    "FROM " + TABLE_SUBS + " tSubs INNER JOIN " + TABLE_NAME + " tEvent ON tSubs.event = tEvent.eventID INNER JOIN " + TABLE_USER + " tUser ON tSubs.user = tUser.userID " +
                    "WHERE tEvent.id = ?"
        );

        addEvent = conn.prepareStatement(
                "INSERT INTO " + TABLE_NAME + "(" +
                        "title, description, start, end, subscription_limit, capacity, place, creator, public" +
                    ") VALUES(?,?,?,?,?,?,?,?,?)"
        );

        updateEventById = conn.prepareStatement(
                "UPDATE " + TABLE_NAME + " " +
                    "SET title = ?, description = ?, start = ?, end = ?, subscription_limit = ?, capacity = ?, place = ?, public = ? " +
                    "WHERE id = ?"
        );

        deleteEventById = conn.prepareStatement(
                "DELETE FROM " + TABLE_NAME + " " +
                    "WHERE id = ?"
        );

        neutralResearch = conn.prepareStatement(
                "SELECT " +
                    "tEvent.id, tEvent.title, tEvent.description, tEvent.created, tEvent.edited, tEvent.start, tEvent.end, tEvent.subscription_limit, tEvent.capacity, tEvent.place, tEvent.creator, tEvent.public, " +
                    "tUser.id userID, tUser.name, tUser.surname, tUser.type userType " +
                    "FROM " + TABLE_NAME + " tEvent INNER JOIN " + TABLE_USER + " tUser ON tEvent.creator = tUser.id " +
                    "WHERE tEvent.end >= current_timestamp() OR 1=? " +
                    "ORDER BY tEvent.start ASC " +
                    "LIMIT ? OFFSET ?"
        );
        dailyResearch = conn.prepareStatement(
                "SELECT " +
                    "tEvent.id, tEvent.title, tEvent.description, tEvent.created, tEvent.edited, tEvent.start, tEvent.end, tEvent.subscription_limit, tEvent.capacity, tEvent.place, tEvent.creator, tEvent.public, " +
                    "tUser.id userID, tUser.name, tUser.surname, tUser.type userType " +
                    "FROM " + TABLE_NAME + " tEvent INNER JOIN " + TABLE_USER + " tUser ON tEvent.creator = tUser.id " +
                    "WHERE DATE(current_timestamp()) BETWEEN DATE(tEvent.start) AND DATE(tEvent.end) " +
                    "ORDER BY tEvent.start ASC " +
                    "LIMIT ? OFFSET ?"
        );
        fullTextResearch = conn.prepareStatement(
                "SELECT " +
                    "tEvent.id, tEvent.title, tEvent.description, tEvent.created, tEvent.edited, tEvent.start, tEvent.end, tEvent.subscription_limit, tEvent.capacity, tEvent.place, tEvent.creator, tEvent.public, " +
                    "tUser.id userID, tUser.name, tUser.surname, tUser.type userType " +
                    "FROM " + TABLE_NAME + " tEvent INNER JOIN " + TABLE_USER + " tUser ON tEvent.creator = tUser.id " +
                    "WHERE " +
                        "(MATCH (tEvent.title, tEvent.description, tEvent.place) AGAINST (?)) " +
                        "AND (tEvent.end >= current_timestamp() OR 1=?) " +
                    "LIMIT ? OFFSET ?"
        );
    }

    public Event createFullEvent(ResultSet rset) throws SQLException {
        NullableResultSet rs = new NullableResultSet(rset);

        Integer id = rs.getInt("id");
        String title = rs.getString("title");
        String description = rs.getString("description");
        Timestamp created = rs.getTimestamp("created");
        Timestamp edited = rs.getTimestamp("edited");
        Timestamp start = rs.getTimestamp("start");
        Timestamp end = rs.getTimestamp("end");
        Timestamp subscription_limit = rs.getTimestamp("subscription_limit");
        Integer capacity = rs.getInt("capacity");
        String place = rs.getString("place");
        Boolean _public = rs.getBoolean("public");
        Integer userID = rs.getInt("userID");
        String name = rs.getString("name");
        String surname = rs.getString("surname");
        String email = rs.getString("email");
        String hash = rs.getString("hash");
        Integer userType = rs.getInt("userType");
        Timestamp userCreated = rs.getTimestamp("createdUser");

        User user = new User(userID, name, surname, email, hash, userType, userCreated);

        return new Event(id, title, description, created, edited, start, end, subscription_limit, capacity, place, user, _public);
    }

    @Override
    public int count() throws SQLException {
        ResultSet rs = count.executeQuery();

        if(rs.next()) {
            int result = rs.getInt(1);
            rs.close();
            return result;
        }

        rs.close();
        return -1;
    }

    public Event get(int id) throws SQLException {
        getEventByID.setInt(1, id);
        ResultSet rs = getEventByID.executeQuery();

        if(rs.next()) {
            Event result = createFullEvent(rs);
            rs.close();
            return result;
        }

        rs.close();
        return null;
    }

    @Override
    public List<Event> research(boolean showPast, int pageSize, int page) throws SQLException {
        neutralResearch.setInt(1, showPast ? 1 : 0);
        neutralResearch.setInt(2, pageSize);
        neutralResearch.setInt(3, (page-1)*pageSize);

        ResultSet rs = neutralResearch.executeQuery();
        List<Event> events = new ArrayList<>();

        while(rs.next()){
            events.add(createFullEvent(rs));
        }

        rs.close(); // TODO mettere il close a tutti
        return events;
    }

    @Override
    public List<Event> research(String query, boolean showPast, int pageSize, int page) throws SQLException {
        fullTextResearch.setString(1, query);
        fullTextResearch.setInt(2, showPast ? 1 : 0);
        fullTextResearch.setInt(3, pageSize);
        fullTextResearch.setInt(4, (page-1)*pageSize);

        ResultSet rs = fullTextResearch.executeQuery();
        List<Event> events = new ArrayList<>();

        while(rs.next()){
            events.add(createFullEvent(rs));
        }

        rs.close(); // TODO mettere il close a tutti
        return events;
    }

    @Override
    public List<Event> research(DateTime date, int pageSize, int page) throws SQLException {
        dailyResearch.setInt(1, pageSize);
        dailyResearch.setInt(2, (page-1)*pageSize);

        ResultSet rs = dailyResearch.executeQuery();
        List<Event> events = new ArrayList<>();

        while(rs.next()){
            events.add(createFullEvent(rs));
        }

        rs.close(); // TODO mettere il close a tutti
        return events;
    }

    @Override
    public void add(Event event) throws SQLException {
        addEvent.setString(1, event.getTitle());
        addEvent.setString(2, event.getDescription());
        addEvent.setTimestamp(3, event.getStart());
        addEvent.setTimestamp(4, event.getEnd());
        addEvent.setTimestamp(5, event.getSubscriptionLimit());
        addEvent.setInt(6, event.getCapacity());
        addEvent.setString(7, event.getPlace());
        addEvent.setInt(8, event.getCreator().getId()); // .getId() perche' creator e' una entity User
        addEvent.setBoolean(9, event.get_public());

        addEvent.execute();
    }

    @Override
    public void update(int id, Event event) throws SQLException {
        updateEventById.setString(1, event.getTitle());
        updateEventById.setString(2, event.getDescription());
        updateEventById.setTimestamp(3, event.getStart());
        updateEventById.setTimestamp(4, event.getEnd());
        updateEventById.setTimestamp(5, event.getSubscriptionLimit());
        updateEventById.setInt(6, event.getCapacity());
        updateEventById.setString(7, event.getPlace());
        updateEventById.setBoolean(8, event.get_public());

        updateEventById.setInt(9, event.getId());

        updateEventById.execute();
    }

    @Override
    public void delete(int id) throws SQLException {
        deleteEventById.setInt(1, id);

        deleteEventById.execute();
    }

    @Override
    protected void close() throws SQLException {
        count.close();
        getEventByID.close();
        listSubcribedByEventId.close();
        addEvent.close();
        updateEventById.close();
        deleteEventById.close();
        neutralResearch.close();
        dailyResearch.close();
        fullTextResearch.close();
    }

}
