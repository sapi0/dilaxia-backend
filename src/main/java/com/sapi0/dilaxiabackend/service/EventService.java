package com.sapi0.dilaxiabackend.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sapi0.dilaxiabackend.data.DaoFactory;
import com.sapi0.dilaxiabackend.data.Mapper;
import com.sapi0.dilaxiabackend.data.dto.*;
import com.sapi0.dilaxiabackend.data.entity.Event;
import com.sapi0.dilaxiabackend.data.entity.User;
import com.sapi0.dilaxiabackend.data.impl.EventDaoImpl;
import com.sapi0.dilaxiabackend.data.impl.UserDaoImpl;
import org.joda.time.DateTime;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EventService {

    private EventDaoImpl dao;

    public EventService() {
        dao = DaoFactory.instance.getEventDao();
    }

    public void createNewEvent(EventCreateDTO dto, int userID, int userType) throws SQLException {
        int eventType = 0;  // TODO TODO TODO !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

        Event event = new Event(-1, dto.title, dto.description, null, null, dto.start, dto.end, dto.subscription_limit, dto.capacity, dto.place, new User(userID, null, null, null, null, userType, null), dto._public);

        dao.add(event);
    }

    public EventDTO getEventByID(int eventID) throws SQLException {
        Event event = dao.get(eventID);

        UserDTO creator = new UserDTO(event.getCreator().getId(), event.getCreator().getName(), event.getCreator().getSurname(), event.getCreator().getType());
        EventDTO dto = new EventDTO(event.getId(), event.getTitle(), event.getDescription(), event.getCreated(), event.getEdited(), event.getStart(), event.getEnd(), event.getSubscriptionLimit(), event.getCapacity(), event.getPlace(), creator, event.get_public());

        return dto;
    }

    public EventDTO editEvent(EventUpdateDTO in, int id) throws SQLException, JsonProcessingException {
        // TODO perform user permission check
        Event event = dao.get(id);

        Event updated = Mapper.update(event, in);

        dao.update(id, updated);

        UserDTO creator = new UserDTO(updated.getCreator().getId(), updated.getCreator().getName(), updated.getCreator().getSurname(), updated.getCreator().getType());
        EventDTO dto = new EventDTO(updated.getId(), updated.getTitle(), updated.getDescription(), updated.getCreated(), updated.getEdited(), updated.getStart(), updated.getEnd(), updated.getSubscriptionLimit(), updated.getCapacity(), updated.getPlace(), creator, updated.get_public());

        return dto;
    }

    public void deleteEvent(int id) throws SQLException {
        // TODO perform user permission check
        dao.delete(id);
    }

    public EventListDTO getEventList(boolean showPast, int pageSize, int page) throws SQLException {
        EventListDTO dto = makeResearch(dao.research(showPast, pageSize, page), pageSize, page);
        dto.filters.showPast = showPast;
        return dto;
    }

    public EventListDTO research(String query, boolean showPast, int pageSize, int page) throws SQLException {
        EventListDTO dto = makeResearch(dao.research(query, showPast, pageSize, page), pageSize, page);
        dto.filters.query = query;
        dto.filters.showPast = showPast;
        return dto;
    }

    public EventListDTO research(DateTime date, int pageSize, int page) throws SQLException {
        EventListDTO dto = makeResearch(dao.research(date, pageSize, page), pageSize, page);
        dto.filters.date = date;
        return dto;
    }

    private EventListDTO makeResearch(List<Event> result, int pageSize, int page) throws SQLException {
        int totalSize = dao.count();
        EventListDTO dto = new EventListDTO();
        dto.pageSize = pageSize;
        dto.page = page;
        dto.totalSize = totalSize;
        dto.filters = new FilterDTO();

        for(Event e : result) {
            UserDTO creator = new UserDTO(e.getCreator().getId(), e.getCreator().getName(), e.getCreator().getSurname(), e.getCreator().getType());
            dto.data.add(new EventDTO(e.getId(), e.getTitle(), e.getDescription(), e.getCreated(), e.getEdited(), e.getStart(), e.getEnd(), e.getSubscriptionLimit(), e.getCapacity(), e.getPlace(), creator, e.get_public()));
        }

        return dto;
    }

}
