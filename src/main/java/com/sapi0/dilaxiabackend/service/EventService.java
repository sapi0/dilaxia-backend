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

    public EventListDTO getAllEvents() throws SQLException {

        List<Event> list = dao.all();
        EventListDTO dto = new EventListDTO();

        for(Event e : list) {
            UserDTO creator = ServiceFactory.instance.getUserService().getUserByID(e.getCreator());
            dto.data.add(new EventDTO(e.getId(), e.getTitle(), e.getDescription(), e.getCreated(), e.getEdited(), e.getStart(), e.getEnd(), e.getSubscriptionLimit(), e.getCapacity(), e.getPlace(), e.getType(), creator, e.get_public()));
        }

        return dto;


    }

    public SubscriptionsDTO getSubscribedUsers(int id) {
        SubscriptionsDTO dto = new SubscriptionsDTO();  // TODO
        return dto;
    }

    public EventDTO editEvent(EventUpdateDTO in, int id) throws SQLException, JsonProcessingException {
        // TODO perform user permission check
        Event event = dao.get(id);

        Event updated = Mapper.update(event, in);

        dao.update(id, updated);

        UserDTO creator = ServiceFactory.instance.getUserService().getUserByID(updated.getCreator());
        EventDTO dto = new EventDTO(updated.getId(), updated.getTitle(), updated.getDescription(), updated.getCreated(), updated.getEdited(), updated.getStart(), updated.getEnd(), updated.getSubscriptionLimit(), updated.getCapacity(), updated.getPlace(), updated.getType(), creator, updated.get_public());

        return dto;
    }

    public void deleteEvent(int id) {
        // TODO perform user permission check
        dao.delete(id);
    }

    // TODO @cri fai un controllo per vedere se e' gia' iscritto
    public EventDTO subscribeUser(int eventID, int userID) throws SQLException {
        dao.subscribe(eventID, userID);

        return getEventByID(eventID);
    }

    // TODO @cri fai un controllo per vedere se e' gia' iscritto
    public EventDTO unsubscribeUser(int eventID, int userID) throws SQLException {
        dao.unsubscribe(eventID, userID);

        return getEventByID(eventID);
    }

    public EventListDTO getEventList(boolean showPast, int pageSize, int page) throws SQLException {
        EventListDTO dto = makeResearch(dao.research(pageSize, page), pageSize, page);
        dto.filters.showPast = showPast;
        return dto;
    }

    public EventListDTO research(String query, boolean showPast, int pageSize, int page) throws SQLException {
        EventListDTO dto = makeResearch(dao.research(query, pageSize, page), pageSize, page);
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
            // TODO appena riusciamo non ci sara' piu' bisogno di fetchare creator a mano. esce dal dao insieme a Event
            UserDTO creator = ServiceFactory.instance.getUserService().getUserByID(e.getCreator());
            dto.data.add(new EventDTO(e.getId(), e.getTitle(), e.getDescription(), e.getCreated(), e.getEdited(), e.getStart(), e.getEnd(), e.getSubscriptionLimit(), e.getCapacity(), e.getPlace(), e.getType(), creator, e.get_public()));
        }

        return dto;
    }

}
