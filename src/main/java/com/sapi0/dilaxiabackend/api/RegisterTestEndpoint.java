package com.sapi0.dilaxiabackend.api;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.io.IOUtils;
import org.json.JSONObject;

import java.io.IOException;
import java.util.regex.Pattern;

@WebServlet(name="registertest", value="/test/register")
public class RegisterTestEndpoint extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String body = IOUtils.toString(request.getReader());
        JSONObject json = new JSONObject(body);

        if(!json.has("name") || !json.has("surname") || !json.has("email") || !json.has("password") || json.keySet().size() > 4) {
            response.sendError(400, "Invalid schema provided");
            return;
        }

        Pattern onlyLettersPattern = Pattern.compile("^[a-zA-Z\\s]+$");
        Pattern emailPattern = Pattern.compile("\\w+([-+.']\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*");
        Pattern passwordPattern = Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,50}$", Pattern.CASE_INSENSITIVE);

        String name = json.getString("name");
        String surname = json.getString("surname");
        String email = json.getString("email");
        String password = json.getString("password");

        if(name.length() < 1 || name.length() >= 50 || !onlyLettersPattern.matcher(name).matches()) {
            response.sendError(496, "Invalid name");
            return;
        }

        if(surname.length() < 1 || surname.length() >= 50 || !onlyLettersPattern.matcher(surname).matches()) {
            response.sendError(497, "Invalid surname");
            return;
        }

        if(email.length() <= 5 || email.length() >= 100 || !emailPattern.matcher(email).matches()) {
            response.sendError(498, "Invalid email");
            return;
        }

        if(!passwordPattern.matcher(password).matches()) {
            response.sendError(499, "Invalid password");
            return;
        }

        response.getWriter().print(new JSONObject().put("name", name).put("surname", surname).put("email", email).put("password", password).toString());

    }

}
