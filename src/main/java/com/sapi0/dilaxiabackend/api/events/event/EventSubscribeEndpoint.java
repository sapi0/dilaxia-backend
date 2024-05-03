package com.sapi0.dilaxiabackend.api.events.event;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sapi0.dilaxiabackend.api.BasicJsonEndpoint;
import com.sapi0.dilaxiabackend.api.PathIntegerJsonEndpoint;
import com.sapi0.dilaxiabackend.data.Mapper;
import com.sapi0.dilaxiabackend.data.dto.EventDTO;
import com.sapi0.dilaxiabackend.exception.EndpointException;
import com.sapi0.dilaxiabackend.service.EventService;
import com.sapi0.dilaxiabackend.service.ServiceFactory;
import com.sapi0.dilaxiabackend.service.SubscriptionService;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpSession;
import org.json.JSONObject;

import java.sql.SQLException;
import java.util.HashMap;

@WebServlet("/path/event/subscribe/*")
public class EventSubscribeEndpoint extends PathIntegerJsonEndpoint {

    private SubscriptionService service;

    @Override
    public void init() {
        service = ServiceFactory.instance.getSubscriptionService();
    }

    @Override
    public void destroy() {

    }

    @Override
    protected JSONObject post(JSONObject bodyObject, HashMap<String, String> headers, HashMap<String, String> queryParams, int pathParam, HttpSession session) throws EndpointException {
        requireLoggedIn(session);

        try {
            return Mapper.asJSON(service.subscribeUser(pathParam, (int)session.getAttribute("id"), (int)session.getAttribute("type")));
        } catch (SQLException | JsonProcessingException e) {
            throw new EndpointException(499, "boh");
        }
    }

    @Override
    protected JSONObject delete(JSONObject bodyObject, HashMap<String, String> headers, HashMap<String, String> queryParams, int pathParam, HttpSession session) throws EndpointException {
        requireLoggedIn(session);

        try {
            return Mapper.asJSON(service.unsubscribeUser(pathParam, (int)session.getAttribute("id")));
        } catch (SQLException | JsonProcessingException e) {
            throw new EndpointException(499, "boh");
        }
    }

}
