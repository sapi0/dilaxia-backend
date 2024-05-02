package com.sapi0.dilaxiabackend.service;

import com.sapi0.dilaxiabackend.data.DaoFactory;
import com.sapi0.dilaxiabackend.data.dto.EventDTO;
import com.sapi0.dilaxiabackend.data.dto.EventLightDTO;
import com.sapi0.dilaxiabackend.data.dto.EventUserDTO;
import com.sapi0.dilaxiabackend.data.dto.SubscriptionListDTO;
import com.sapi0.dilaxiabackend.data.entity.Event;
import com.sapi0.dilaxiabackend.data.entity.Subscription;
import com.sapi0.dilaxiabackend.data.impl.SubscriptionDaoImpl;

import java.sql.SQLException;
import java.util.List;

public class SubscriptionService {

    private SubscriptionDaoImpl dao;

    public SubscriptionService() {
        dao = DaoFactory.instance.getSubscriptionDao();
    }

    public SubscriptionListDTO getSubscribedUsers(int eventID) throws SQLException {
        SubscriptionListDTO dto = new SubscriptionListDTO();

        List<Subscription> result = dao.getSubscribedUsers(eventID);

        Event event;

        if(result.size() == 0) {
            event = DaoFactory.instance.getEventDao().get(eventID);
        } else {
            event = result.get(0).getEvent();
        }

        dto.event = new EventLightDTO(event.getId(), event.getTitle(), event.getCapacity());
        for(Subscription sub : result) {
            EventUserDTO eventUser = new EventUserDTO(sub.getUser().getId(), sub.getUser().getName(), sub.getUser().getSurname(), sub.getTimestamp());

            if(dto.subscribed.size() >= event.getCapacity())
                dto.queued.add(eventUser);
            else
                dto.subscribed.add(eventUser);
        }

        return dto;
    }

    // TODO @cri fai un controllo per vedere se e' gia' iscritto
    public EventDTO subscribeUser(int eventID, int userID) throws SQLException {
        dao.insertSubscription(eventID, userID);

        return ServiceFactory.instance.getEventService().getEventByID(eventID);
    }

    // TODO @cri fai un controllo per vedere se e' gia' iscritto
    public EventDTO unsubscribeUser(int eventID, int userID) throws SQLException {
        dao.deleteSubscription(eventID);

        return ServiceFactory.instance.getEventService().getEventByID(eventID);
    }

}
