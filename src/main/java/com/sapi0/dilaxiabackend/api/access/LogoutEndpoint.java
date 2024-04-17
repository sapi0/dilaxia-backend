package com.sapi0.dilaxiabackend.api.access;

import com.sapi0.dilaxiabackend.api.BasicJsonEndpoint;
import com.sapi0.dilaxiabackend.exception.AccessException;
import com.sapi0.dilaxiabackend.exception.EndpointException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpSession;
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
    protected JSONObject post(JSONObject bodyObject, HashMap<String, String> headers, HashMap<String, String> queryParams, HttpSession session) throws EndpointException {
        if(session == null) {
            throw new AccessException(499, "Not logged in");
        }

        try {
            session.invalidate();
        } catch(IllegalStateException e) {
            throw new AccessException(499, "Already logged out");
        }

        return DEFAULT_SUCCESS_JSON;
    }

}
