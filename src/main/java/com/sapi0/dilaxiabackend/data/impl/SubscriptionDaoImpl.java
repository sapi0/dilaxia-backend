package com.sapi0.dilaxiabackend.data.impl;

import com.sapi0.dilaxiabackend.data.dao.ISubscriptionDao;
import com.sapi0.dilaxiabackend.data.entity.Event;
import com.sapi0.dilaxiabackend.data.entity.Subscription;
import com.sapi0.dilaxiabackend.data.entity.User;
import com.sapi0.dilaxiabackend.utils.NullableResultSet;

import javax.naming.NamingException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class SubscriptionDaoImpl extends DaoImpl implements ISubscriptionDao {

    private static final String TABLE_NAME = "subscription";
    private static final String TABLE_USER = "user";
    private static final String TABLE_EVENT = "event";

    private PreparedStatement count, listSubscribedByEventId, subscribeToEvent, unsubscribeFromEvent;

    public SubscriptionDaoImpl() throws NamingException, SQLException {
        super();
    }

    @Override
    protected void initStatements() throws SQLException {
        count = conn.prepareStatement("SELECT COUNT(id) FROM " + TABLE_NAME);
        listSubscribedByEventId = conn.prepareStatement(
                "SELECT " +
                    "tSubs.id, tSubs.timestamp tEvent.id eventID, tEvent.title, tEvent.capacity, tUser.id userID, tUser.name, tUser.surname" +
                    "FROM " + TABLE_NAME + " tSubs INNER JOIN " + TABLE_EVENT + " tEvent ON tSubs.event = tEvent.eventID INNER JOIN " + TABLE_USER + " tUser ON tSubs.user = tUser.userID" +
                    "WHERE tEvent.id = ?"
        );
        subscribeToEvent = conn.prepareStatement(
                "INSERT INTO " + TABLE_NAME + "(" +
                        "event, user" +
                    ") VALUES (?,?)"
        );
        unsubscribeFromEvent = conn.prepareStatement(
                "DELETE FROM " + TABLE_NAME + " " +
                    "WHERE id = ?"
        );
    }

    public Subscription createSubscription(ResultSet rset) throws SQLException {
        NullableResultSet rs = new NullableResultSet(rset);

        Integer id = rs.getInt("id");
        Timestamp timestamp = rs.getTimestamp("timestamp");
        Integer eventID = rs.getInt("eventID");
        String title = rs.getString("title");
        Integer capacity = rs.getInt("capacity");
        Integer userID = rs.getInt("userID");
        String name = rs.getString("name");
        String surname = rs.getString("surname");

        User user = new User(userID, name, surname, null, null, null, null);
        Event event = new Event(eventID, title, null, null, null, null, null, null, capacity, null, null, null);

        return new Subscription(id, timestamp, event, user);
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

    @Override
    public List<Subscription> getSubscribedUsers(int eventID) throws SQLException {
        listSubscribedByEventId.setInt(1, eventID);

        ResultSet rs = listSubscribedByEventId.executeQuery();
        List<Subscription> subs = new ArrayList<>();

        while(rs.next()){
            subs.add(createSubscription(rs));
        }

        rs.close();
        return subs;
    }

    @Override
    public void insertSubscription(int eventID, int userID) throws SQLException {
        subscribeToEvent.setInt(1, eventID);
        subscribeToEvent.setInt(2, userID);

        subscribeToEvent.execute();
    }

    @Override
    public void deleteSubscription(int eventID) throws SQLException {
        unsubscribeFromEvent.setInt(1, eventID);

        unsubscribeFromEvent.execute();
    }

    @Override
    protected void close() throws SQLException {
        count.close();
        listSubscribedByEventId.close();
        subscribeToEvent.close();
        unsubscribeFromEvent.close();
    }

}
