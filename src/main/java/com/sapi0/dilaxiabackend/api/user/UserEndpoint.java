package com.sapi0.dilaxiabackend.api.user;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sapi0.dilaxiabackend.api.BasicJsonEndpoint;
import com.sapi0.dilaxiabackend.api.PathIntegerJsonEndpoint;
import com.sapi0.dilaxiabackend.data.Mapper;
import com.sapi0.dilaxiabackend.exception.EndpointException;
import com.sapi0.dilaxiabackend.service.ServiceFactory;
import com.sapi0.dilaxiabackend.service.UserService;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpSession;
import org.json.JSONObject;

import java.sql.SQLException;
import java.util.HashMap;

@WebServlet("/path/user/*")
public class UserEndpoint extends PathIntegerJsonEndpoint {

    private UserService service;

    @Override
    public void init() {
        service = ServiceFactory.instance.getUserService();
    }

    @Override
    public void destroy() {

    }

    @Override
    protected JSONObject get(HashMap<String, String> headers, HashMap<String, String> queryParams, int pathParam, HttpSession session) throws EndpointException {
        requireLoggedIn(session);

        try {
            return Mapper.asJSON(service.getUserByID(pathParam));
        } catch (SQLException | JsonProcessingException e) {
            throw new EndpointException(499, "");
        }
    }

    @Override
    protected JSONObject put(JSONObject bodyObject, HashMap<String, String> headers, HashMap<String, String> queryParams, int pathParam, HttpSession session) throws EndpointException {
        return super.put(bodyObject, headers, queryParams, session);
    }

    @Override
    protected JSONObject delete(JSONObject bodyObject, HashMap<String, String> headers, HashMap<String, String> queryParams, int pathParam, HttpSession session) throws EndpointException {
        return super.delete(bodyObject, headers, queryParams, session);
    }

}
