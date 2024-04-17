package com.sapi0.dilaxiabackend.api.events.event;

import com.sapi0.dilaxiabackend.api.BasicJsonEndpoint;
import com.sapi0.dilaxiabackend.exception.EndpointException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpSession;
import org.json.JSONObject;

import java.util.HashMap;

@WebServlet("/path/event/subscribe/*")
public class EventSubscribeEndpoint extends BasicJsonEndpoint {

    @Override
    public void init() {

    }

    @Override
    public void destroy() {

    }

    @Override
    protected JSONObject post(JSONObject bodyObject, HashMap<String, String> headers, HashMap<String, String> queryParams, HttpSession session) throws EndpointException {
        return super.post(bodyObject, headers, queryParams, session);
    }

    @Override
    protected JSONObject delete(JSONObject bodyObject, HashMap<String, String> headers, HashMap<String, String> queryParams, HttpSession session) throws EndpointException {
        return super.delete(bodyObject, headers, queryParams, session);
    }

}
