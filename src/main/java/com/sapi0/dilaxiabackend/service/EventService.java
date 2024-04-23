package com.sapi0.dilaxiabackend.service;

import com.sapi0.dilaxiabackend.data.DaoFactory;
import com.sapi0.dilaxiabackend.data.dto.EventCreateDTO;
import com.sapi0.dilaxiabackend.data.dto.EventDTO;
import com.sapi0.dilaxiabackend.data.dto.UserDTO;
import com.sapi0.dilaxiabackend.data.entity.Event;
import com.sapi0.dilaxiabackend.data.entity.User;
import com.sapi0.dilaxiabackend.data.impl.EventDaoImpl;
import com.sapi0.dilaxiabackend.data.impl.UserDaoImpl;

import java.sql.SQLException;

public class EventService {

    private UserService userService;

    private EventDaoImpl dao;

    public EventService() {
        dao = DaoFactory.instance.getEventDao();
    }

    public void createNewEvent(EventCreateDTO dto, int userID, int userType) throws SQLException {
        int type = 0;

        Event event = new Event(-1, dto.title, dto.description, null, null, dto.start, dto.end, dto.subscription_limit, dto.capacity, dto.place, type, userID, dto._public);

        dao.add(event);

    }

    public EventDTO getEventByID(int eventID) throws SQLException {
        Event event = dao.get(eventID);

        UserDTO creator = ServiceFactory.instance.getUserService().getUserByID(event.getCreator());

        EventDTO dto = new EventDTO(event.getId(), event.getTitle(), event.getDescription(), event.getCreated(), event.getEdited(), event.getStart(), event.getEnd(), event.getSubscriptionLimit(), event.getCapacity(), event.getPlace(), event.getType(), creator, event.get_public());
        return dto;
    }

}
