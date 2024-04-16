package com.sapi0.dilaxiabackend.api.access;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.sapi0.dilaxiabackend.data.DaoFactory;
import com.sapi0.dilaxiabackend.data.Mapper;
import com.sapi0.dilaxiabackend.data.dto.UserDTO;
import com.sapi0.dilaxiabackend.data.entity.User;
import com.sapi0.dilaxiabackend.data.impl.UserDaoImpl;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name="register", value="/register")
public class RegisterEndpointOLD extends HttpServlet {

    private UserDaoImpl dao;

    public void init() {
        dao = DaoFactory.instance.getUserDao();
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        UserDTO userData = Mapper.asObject(IOUtils.toString(request.getReader()), UserDTO.class);

        // TODO qui fai i controlli sui dati inseriti dall'utente

        String bcryptHashString = BCrypt.withDefaults().hashToString(15, "".toCharArray());

        User user = new User(-1, userData.name, userData.surname, userData.email, bcryptHashString, userData.birth, userData.type);

        try {
            dao.add(user);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void destroy() {

    }

}
