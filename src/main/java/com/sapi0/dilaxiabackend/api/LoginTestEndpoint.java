package com.sapi0.dilaxiabackend.api;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.io.IOUtils;
import org.json.JSONObject;

import java.io.IOException;

@WebServlet(name="logintest", value="/test/login")
public class LoginTestEndpoint extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String body = IOUtils.toString(request.getReader());
        JSONObject json = new JSONObject(body);

        if(!json.has("email") || !json.has("password")) {
            response.sendError(400, "schema non valido");
            return;
        }

        if(!json.getString("email").equals("sapioandrea@gmail.com") || !json.getString("password").equals("aldini2020?")) {
            response.sendError(400, "account con email e password non trovati");
            return;
        }

        response.getWriter().print(new JSONObject().put("name", "Andrea").put("surname", "Sapio").toString());

    }

}
