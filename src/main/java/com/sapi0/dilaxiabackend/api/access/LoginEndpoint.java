package com.sapi0.dilaxiabackend.api.access;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.sapi0.dilaxiabackend.api.BasicJsonEndpoint;
import com.sapi0.dilaxiabackend.data.DaoFactory;
import com.sapi0.dilaxiabackend.data.Mapper;
import com.sapi0.dilaxiabackend.data.dto.LoginDTO;
import com.sapi0.dilaxiabackend.data.entity.User;
import com.sapi0.dilaxiabackend.data.impl.UserDaoImpl;
import com.sapi0.dilaxiabackend.exception.AccessException;
import com.sapi0.dilaxiabackend.exception.BodyParseException;
import com.sapi0.dilaxiabackend.exception.EndpointException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
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
    protected JSONObject post(HttpServletRequest request, HttpServletResponse response, JSONObject bodyObject, HashMap<String, String> headers, HashMap<String, String> queryParams) throws EndpointException {
        requireLoggedOut(request.getSession(false));

        try {
            LoginDTO loginData = Mapper.asObject(bodyObject, LoginDTO.class);

            User user = dao.get(loginData.email);

            if(user == null) {
                throw new AccessException(499, "Wrong credentials");
            }

            BCrypt.Result result = BCrypt.verifyer().verify(loginData.password.toCharArray(), user.getHash());

            if(!result.verified) {
                throw new AccessException(499, "Wrong credentials");
            }

            // se arriva qui vuol dire che si puo' loggare
            HttpSession session = request.getSession(true);

            session.setAttribute("id", user.getId());
            session.setAttribute("type", user.getType());
            session.setAttribute("logged", true);

            // return new JSONObject().put("id", user.id).put("sessionCookie", session.getId()); // probabilmente non e' una buona idea dare queste info cosi' a caso
            return DEFAULT_SUCCESS_JSON;
        } catch (JsonProcessingException e) {
            throw new BodyParseException(499, "Invalid body object");
        } catch (SQLException e) {
            // TODO throw
        }

        throw new EndpointException(499, "Unknown error");
    }

}
