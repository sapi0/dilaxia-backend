package com.sapi0.dilaxiabackend.api.user.me;

import com.sapi0.dilaxiabackend.api.BasicJsonEndpoint;
import jakarta.servlet.annotation.WebServlet;
import org.json.JSONObject;

import java.util.HashMap;

@WebServlet("/me")
public class MeEndpoint  extends BasicJsonEndpoint {

    @Override
    public void init() {

    }

    @Override
    public void destroy() {

    }

    @Override
    protected JSONObject get(HashMap<String, String> headers) {
        return super.get(headers);
    }

    @Override
    protected JSONObject put(JSONObject bodyObject, HashMap<String, String> headers) {
        return super.put(bodyObject, headers);
    }

    @Override
    protected JSONObject delete(JSONObject bodyObject, HashMap<String, String> headers) {
        return super.delete(bodyObject, headers);
    }

}
