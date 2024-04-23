package com.sapi0.dilaxiabackend.service;

import com.sapi0.dilaxiabackend.data.impl.EventDaoImpl;
import com.sapi0.dilaxiabackend.data.impl.UserDaoImpl;

import javax.naming.NamingException;
import java.sql.SQLException;

public class ServiceFactory {

    public static final ServiceFactory instance;
    static {
        instance = new ServiceFactory();
    }

    private UserService userService;
    private EventService eventService;

    private ServiceFactory() {
        userService = new UserService();
        eventService = new EventService();
    }

    public UserService getUserService() {
        return userService;
    }
    public EventService getEventService() {return eventService;}

}
