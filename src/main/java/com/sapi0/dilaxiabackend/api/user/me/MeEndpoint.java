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
    protected JSONObject get(HashMap<String, String> headers, HashMap<String, String> queryParams) {
        return super.get(headers, queryParams);
    }

    @Override
    protected JSONObject put(JSONObject bodyObject, HashMap<String, String> headers, HashMap<String, String> queryParams) {
        return super.put(bodyObject, headers, queryParams);
    }

    @Override
    protected JSONObject delete(JSONObject bodyObject, HashMap<String, String> headers, HashMap<String, String> queryParams) {
        return super.delete(bodyObject, headers, queryParams);
    }

}
