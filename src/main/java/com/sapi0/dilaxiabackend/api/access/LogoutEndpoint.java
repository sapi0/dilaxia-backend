package com.sapi0.dilaxiabackend.api.access;

import com.sapi0.dilaxiabackend.api.BasicJsonEndpoint;
import jakarta.servlet.annotation.WebServlet;
import org.json.JSONObject;

import java.util.HashMap;

@WebServlet("/logout")
public class LogoutEndpoint extends BasicJsonEndpoint {

    @Override
    public void init() {

    }

    @Override
    public void destroy() {

    }

    @Override
    protected JSONObject post(JSONObject bodyObject, HashMap<String, String> headers, HashMap<String, String> queryParams) {
        return super.post(bodyObject, headers, queryParams);
    }

}
