package com.sapi0.dilaxiabackend.api.user.me;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sapi0.dilaxiabackend.api.BasicJsonEndpoint;
import com.sapi0.dilaxiabackend.data.Mapper;
import com.sapi0.dilaxiabackend.data.dto.UserUpdateDTO;
import com.sapi0.dilaxiabackend.exception.EndpointException;
import com.sapi0.dilaxiabackend.service.ServiceFactory;
import com.sapi0.dilaxiabackend.service.UserService;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpSession;
import org.json.JSONObject;

import java.sql.SQLException;
import java.util.HashMap;

@WebServlet("/me")
public class MeEndpoint  extends BasicJsonEndpoint {

    private UserService service;

    @Override
    public void init() {
        service = ServiceFactory.instance.getUserService();
    }

    @Override
    public void destroy() {

    }

    @Override
    protected JSONObject get(HashMap<String, String> headers, HashMap<String, String> queryParams, HttpSession session) throws EndpointException {
        requireLoggedIn(session);

        try {
            return Mapper.asJSON(service.getUserMe((int)session.getAttribute("id")));
        } catch (SQLException | JsonProcessingException e) {
            throw new EndpointException(499, "boh");
        }
    }

    @Override
    protected JSONObject put(JSONObject bodyObject, HashMap<String, String> headers, HashMap<String, String> queryParams, HttpSession session) throws EndpointException {
        requireLoggedIn(session);

        try {
            return Mapper.asJSON(service.editUserMe(Mapper.asObject(bodyObject, UserUpdateDTO.class), (int)session.getAttribute("id")));
        } catch (SQLException | JsonProcessingException e) {
            throw new EndpointException(499, "boh");
        }
    }

    @Override
    protected JSONObject delete(JSONObject bodyObject, HashMap<String, String> headers, HashMap<String, String> queryParams, HttpSession session) throws EndpointException {
        requireLoggedIn(session);

        // TODO qui ovviamente servono i permessi e non puo' essere cosi' facile eliminare un account

        try {
            service.deleteUser((int)session.getAttribute("id"));
        } catch (SQLException e) {
            throw new EndpointException(499, "boh");
        }

        return DEFAULT_SUCCESS_JSON;
    }

}
