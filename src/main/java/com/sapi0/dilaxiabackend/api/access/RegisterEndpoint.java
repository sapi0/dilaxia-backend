package com.sapi0.dilaxiabackend.api.access;

import com.sapi0.dilaxiabackend.api.BasicJsonEndpoint;
import com.sapi0.dilaxiabackend.data.DaoFactory;
import com.sapi0.dilaxiabackend.data.impl.UserDaoImpl;
import com.sapi0.dilaxiabackend.exception.EndpointException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpSession;
import org.json.JSONObject;

import java.util.HashMap;

@WebServlet("/register")
public class RegisterEndpoint extends BasicJsonEndpoint {

    private UserDaoImpl dao;

    @Override
    public void init() {
        dao = DaoFactory.instance.getUserDao();
    }

    @Override
    public void destroy() {

    }

    @Override
    protected JSONObject post(JSONObject bodyObject, HashMap<String, String> headers, HashMap<String, String> queryParams, HttpSession session) throws EndpointException {
        return super.post(bodyObject, headers, queryParams, session);
    }

}
