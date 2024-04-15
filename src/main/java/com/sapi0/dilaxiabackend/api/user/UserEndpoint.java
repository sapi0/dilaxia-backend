package com.sapi0.dilaxiabackend.api.user;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sapi0.dilaxiabackend.data.DaoFactory;
import com.sapi0.dilaxiabackend.data.Mapper;
import com.sapi0.dilaxiabackend.data.entity.User;
import com.sapi0.dilaxiabackend.data.impl.UserDaoImpl;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.io.IOUtils;

import javax.naming.NamingException;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "user", value = "/user")
public class UserEndpoint extends HttpServlet {

    private UserDaoImpl dao;

    public void init() {
        dao = DaoFactory.instance.getUserDao();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json");

        if(request.getHeader("id") != null) {
            int id = Integer.parseInt(request.getHeader("id"));
            try {
                response.getWriter().print(Mapper.asJSON(dao.get(id)));
            } catch (SQLException e) {
                response.sendError(400, "probabilmente non va la query o l'id che hai messo e' scazzato");
                throw new RuntimeException(e);
            }
            return;
        }

        // se non usa l'id e' un getAll
        try {
            response.getWriter().print(Mapper.asJSON(dao.all()));
        } catch (SQLException e) {
            response.sendError(400, "boh non ho idea di come possa succedere");
            throw new RuntimeException(e);
        }

    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        User user = Mapper.asObject(IOUtils.toString(request.getReader()), User.class);
        try {
            dao.add(user);
        } catch (SQLException e) {
            response.sendError(400, "non so perche'");
            e.printStackTrace();
            throw new RuntimeException(e);
        }

        response.getWriter().print("done");
    }

    public void destroy() {

    }

}
