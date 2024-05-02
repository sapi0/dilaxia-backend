package com.sapi0.dilaxiabackend.api.events.event;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sapi0.dilaxiabackend.api.BasicJsonEndpoint;
import com.sapi0.dilaxiabackend.api.PathIntegerJsonEndpoint;
import com.sapi0.dilaxiabackend.data.Mapper;
import com.sapi0.dilaxiabackend.data.dto.EventUpdateDTO;
import com.sapi0.dilaxiabackend.data.dto.UserUpdateDTO;
import com.sapi0.dilaxiabackend.exception.BodyParseException;
import com.sapi0.dilaxiabackend.exception.EndpointException;
import com.sapi0.dilaxiabackend.service.EventService;
import com.sapi0.dilaxiabackend.service.ServiceFactory;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpSession;
import org.json.JSONObject;

import java.sql.SQLException;
import java.util.HashMap;

@WebServlet("/path/event/*")
public class EventIdEndpoint extends PathIntegerJsonEndpoint {

    private EventService service;

    @Override
    public void init() {
        service = ServiceFactory.instance.getEventService();
    }

    @Override
    public void destroy() {

    }

    @Override
    protected JSONObject get(HashMap<String, String> headers, HashMap<String, String> queryParams, int pathParam, HttpSession session) throws EndpointException {
        requireLoggedIn(session);

        try {
            return Mapper.asJSON(service.getEventByID(pathParam));
        } catch(JsonProcessingException e) {
            // TODO
            throw new BodyParseException(599, "Can't convert the response to json. server error");
        } catch(SQLException e) {
            e.printStackTrace();
            // TODO
            throw new EndpointException(499, "Unknown event ID");
        }
    }

    @Override
    protected JSONObject put(JSONObject bodyObject, HashMap<String, String> headers, HashMap<String, String> queryParams, int pathParam, HttpSession session) throws EndpointException {
        requireLoggedIn(session);

        try {
            return Mapper.asJSON(service.editEvent(Mapper.asObject(bodyObject, EventUpdateDTO.class), pathParam));
        } catch (SQLException | JsonProcessingException e) {
            throw new EndpointException(499, "boh");
        }
    }

    @Override
    protected JSONObject delete(JSONObject bodyObject, HashMap<String, String> headers, HashMap<String, String> queryParams, int pathParam, HttpSession session) throws EndpointException {
        requireLoggedIn(session);

        try {
            service.deleteEvent(pathParam);
        } catch (SQLException e) {
            throw new EndpointException(499, "boh");
        }

        return DEFAULT_SUCCESS_JSON;
    }

}
