package com.sapi0.dilaxiabackend.api.access;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.sapi0.dilaxiabackend.api.BasicJsonEndpoint;
import com.sapi0.dilaxiabackend.data.DaoFactory;
import com.sapi0.dilaxiabackend.data.Mapper;
import com.sapi0.dilaxiabackend.data.dto.LoginDTO;
import com.sapi0.dilaxiabackend.data.entity.User;
import com.sapi0.dilaxiabackend.data.impl.UserDaoImpl;
import com.sapi0.dilaxiabackend.exception.BodyParseException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.io.IOUtils;
import org.json.JSONObject;

import java.sql.SQLException;
import java.util.HashMap;

@WebServlet("/login")
public class LoginEndpoint extends BasicJsonEndpoint {

    private UserDaoImpl dao;

    @Override
    public void init() {
        dao = DaoFactory.instance.getUserDao();
    }

    @Override
    public void destroy() {

    }

    // ECCEZZIONE: in questo caso la session dobbiamo crearla siccome vuole fare il login, quindi override del basic senza usare il tipo di Endpoint per sessioni
    @Override
    protected JSONObject post(HttpServletRequest request, HttpServletResponse response, JSONObject bodyObject, HashMap<String, String> headers, HashMap<String, String> queryParams) throws BodyParseException {
        try {
            LoginDTO loginData = Mapper.asObject(bodyObject, LoginDTO.class);

            User user = dao.get(loginData.email);

            BCrypt.Result result = BCrypt.verifyer().verify(loginData.password.toCharArray(), user.hash);

            if(!result.verified) {
                // TODO throw
            }

            // se arriva qui vuol dire che si puo' loggare


        } catch (JsonProcessingException e) {
            throw new BodyParseException("Invalid body object");
        } catch (SQLException e) {
            // TODO throw
        }
    }

}
