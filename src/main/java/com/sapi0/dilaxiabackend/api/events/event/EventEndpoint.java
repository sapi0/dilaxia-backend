package com.sapi0.dilaxiabackend.api.events.event;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sapi0.dilaxiabackend.api.BasicJsonEndpoint;
import com.sapi0.dilaxiabackend.data.DaoFactory;
import com.sapi0.dilaxiabackend.data.Mapper;
import com.sapi0.dilaxiabackend.data.dto.EventCreateDTO;
import com.sapi0.dilaxiabackend.data.impl.EventDaoImpl;
import com.sapi0.dilaxiabackend.data.impl.UserDaoImpl;
import com.sapi0.dilaxiabackend.exception.BodyParseException;
import com.sapi0.dilaxiabackend.exception.EndpointException;
import com.sapi0.dilaxiabackend.service.EventService;
import com.sapi0.dilaxiabackend.service.ServiceFactory;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpSession;
import org.json.JSONObject;

import java.sql.SQLException;
import java.util.HashMap;

@WebServlet("/event")
public class EventEndpoint extends BasicJsonEndpoint {

    private EventService service;

    @Override
    public void init() {
        service = ServiceFactory.instance.getEventService();
    }

    @Override
    public void destroy() {

    }

    @Override
    protected JSONObject post(JSONObject bodyObject, HashMap<String, String> headers, HashMap<String, String> queryParams, HttpSession session) throws EndpointException {
        requireLoggedIn(session);

        try {
            EventCreateDTO eventData = Mapper.asObject(bodyObject, EventCreateDTO.class);

            System.out.println((int)session.getAttribute("id"));

            service.createNewEvent(eventData, (int)session.getAttribute("id"), (int)session.getAttribute("type"));

        } catch(JsonProcessingException e) {
            throw new BodyParseException(499, "Invalid body object");
        } catch (SQLException e) {
            e.printStackTrace();
            // TODO throw
            throw new EndpointException(499, "Can't add the event");
        }

        // TODO
        throw new EndpointException(599, "non so ora TODO");
    }

}
