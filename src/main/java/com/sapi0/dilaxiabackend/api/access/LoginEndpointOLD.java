package com.sapi0.dilaxiabackend.api.access;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.sapi0.dilaxiabackend.data.DaoFactory;
import com.sapi0.dilaxiabackend.data.Mapper;
import com.sapi0.dilaxiabackend.data.dto.LoginDTO;
import com.sapi0.dilaxiabackend.data.entity.User;
import com.sapi0.dilaxiabackend.data.impl.UserDaoImpl;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name="login", value="/login")
public class LoginEndpointOLD extends HttpServlet {

    private UserDaoImpl dao;

    public void init() {
        dao = DaoFactory.instance.getUserDao();
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        LoginDTO loginData = Mapper.asObject(IOUtils.toString(request.getReader()), LoginDTO.class);

        try {
            User user = dao.get(loginData.email);

            BCrypt.Result result = BCrypt.verifyer().verify(loginData.password.toCharArray(), user.hash);

            if(result.verified) {

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }



    }

    public void destroy() {

    }

}
