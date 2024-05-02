package com.sapi0.dilaxiabackend.data;

import com.sapi0.dilaxiabackend.data.impl.DaoImpl;
import com.sapi0.dilaxiabackend.data.impl.EventDaoImpl;
import com.sapi0.dilaxiabackend.data.impl.SubscriptionDaoImpl;
import com.sapi0.dilaxiabackend.data.impl.UserDaoImpl;

import javax.naming.NamingException;
import java.sql.SQLException;

public class DaoFactory {

    public static final DaoFactory instance;
    static {
        instance = new DaoFactory();
    }

    private UserDaoImpl userDao;
    private EventDaoImpl eventDao;
    private SubscriptionDaoImpl subscriptionDao;

    private DaoFactory() {
        try {
            userDao = new UserDaoImpl();
            eventDao = new EventDaoImpl();
            subscriptionDao = new SubscriptionDaoImpl();
        } catch (NamingException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public UserDaoImpl getUserDao() {
        return userDao;
    }
    public EventDaoImpl getEventDao() {return eventDao;}
    public SubscriptionDaoImpl getSubscriptionDao() {return subscriptionDao;}

}
