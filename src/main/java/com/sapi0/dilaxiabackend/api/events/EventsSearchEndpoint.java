package com.sapi0.dilaxiabackend.api.events;

import com.sapi0.dilaxiabackend.api.BasicJsonEndpoint;
import com.sapi0.dilaxiabackend.exception.EndpointException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpSession;
import org.json.JSONObject;

import java.util.HashMap;

@WebServlet("/events/search")
public class EventsSearchEndpoint extends BasicJsonEndpoint {

    @Override
    public void init() {

    }

    @Override
    public void destroy() {

    }

    @Override
    protected JSONObject get(HashMap<String, String> headers, HashMap<String, String> queryParams, HttpSession session) throws EndpointException {
        return super.get(headers, queryParams, session);
    }

}
