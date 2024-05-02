package com.sapi0.dilaxiabackend.data.dao;

import com.sapi0.dilaxiabackend.data.entity.Subscription;

import java.sql.SQLException;
import java.util.List;

public interface ISubscriptionDao {

    int count() throws SQLException;
    List<Subscription> getSubscribedUsers(int eventID) throws SQLException;
    void insertSubscription(int eventID, int userID) throws SQLException;
    void deleteSubscription(int eventID) throws SQLException;

}
