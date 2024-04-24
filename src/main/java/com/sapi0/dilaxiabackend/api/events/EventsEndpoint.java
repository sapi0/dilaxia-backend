package com.sapi0.dilaxiabackend.api.events;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sapi0.dilaxiabackend.api.BasicJsonEndpoint;
import com.sapi0.dilaxiabackend.data.Mapper;
import com.sapi0.dilaxiabackend.exception.BodyParseException;
import com.sapi0.dilaxiabackend.exception.EndpointException;
import com.sapi0.dilaxiabackend.service.EventService;
import com.sapi0.dilaxiabackend.service.ServiceFactory;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpSession;
import org.json.JSONObject;

import java.sql.SQLException;
import java.util.HashMap;

@WebServlet("/events")
public class EventsEndpoint extends BasicJsonEndpoint {

    private EventService service;

    @Override
    public void init() {
        service = ServiceFactory.instance.getEventService();
    }

    @Override
    public void destroy() {

    }

    @Override
    protected JSONObject get(HashMap<String, String> headers, HashMap<String, String> queryParams, HttpSession session) throws EndpointException {
        requireLoggedIn(session);

        try {
            return Mapper.asJSON(service.getAllEvents());
        } catch(JsonProcessingException e) {
            // TODO
            throw new BodyParseException(599, "Can't convert the response to json. server error");
        } catch(SQLException e) {
            e.printStackTrace();
            // TODO
            throw new EndpointException(499, "Unknown event ID");
        }
    }

}
