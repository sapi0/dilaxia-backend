package com.sapi0.dilaxiabackend.api.events.event;

import com.sapi0.dilaxiabackend.api.BasicJsonEndpoint;
import jakarta.servlet.annotation.WebServlet;
import org.json.JSONObject;

import java.util.HashMap;

@WebServlet("/event/{id}/subscribe")
public class EventSubscribeEndpoint extends BasicJsonEndpoint {

    @Override
    public void init() {

    }

    @Override
    public void destroy() {

    }

    @Override
    protected JSONObject post(JSONObject bodyObject, HashMap<String, String> headers) {
        return super.post(bodyObject, headers);
    }

    @Override
    protected JSONObject delete(JSONObject bodyObject, HashMap<String, String> headers) {
        return super.delete(bodyObject, headers);
    }

}
