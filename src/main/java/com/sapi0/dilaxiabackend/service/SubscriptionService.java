package com.sapi0.dilaxiabackend.service;

import com.sapi0.dilaxiabackend.data.DaoFactory;
import com.sapi0.dilaxiabackend.data.dto.EventDTO;
import com.sapi0.dilaxiabackend.data.dto.EventLightDTO;
import com.sapi0.dilaxiabackend.data.dto.EventUserDTO;
import com.sapi0.dilaxiabackend.data.dto.SubscriptionListDTO;
import com.sapi0.dilaxiabackend.data.entity.Event;
import com.sapi0.dilaxiabackend.data.entity.Subscription;
import com.sapi0.dilaxiabackend.data.impl.SubscriptionDaoImpl;
import com.sapi0.dilaxiabackend.utils.Global;

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

    public EventDTO subscribeUser(int eventID, int userID, int userType) throws SQLException {
        if(isSubscribed(userID, eventID)) {
            throw new SQLException();   // TODO gia' iscritto
        }

        if(userType == Global.USER_TYPE_PROFESSOR) {
            throw new SQLException();   // TODO sei un prof, non ti puoi iscrivere
        }

        EventDTO event = ServiceFactory.instance.getEventService().getEventByID(eventID);   // metto qui perche' prima non serve, farebbe solo una chiamata inutile al db se poi esce fuori un throw
        if(userType == Global.USER_TYPE_EXTERNAL) {
            if(event.creator.type == Global.USER_TYPE_PROFESSOR) {
                throw new SQLException();   // TODO sei un esterno, non puoi iscriverti a quelli creati dai prof
            }
        }

        dao.insertSubscription(eventID, userID);

        return event;
    }

    public EventDTO unsubscribeUser(int eventID, int userID) throws SQLException {
        if(!isSubscribed(userID, eventID)) {
            throw new SQLException();   // TODO non iscritto
        }

        dao.deleteSubscription(eventID);

        return ServiceFactory.instance.getEventService().getEventByID(eventID);
    }

    private boolean isSubscribed(int userID, int eventID) throws SQLException {
        List<Subscription> subscriptions = dao.getSubscribedUsers(eventID);
        for(Subscription subscription : subscriptions) {
            if(subscription.getUser().getId() == userID)
                return true;
        }
        return false;
    }

}
