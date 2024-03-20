package com.sapi0.dilaxiabackend;

import java.io.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import javax.naming.NamingException;

@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class HelloServlet extends HttpServlet {
    private String message;

    public void init() {
        message = "Hello World!";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json");

        // Hello
        PrintWriter out = response.getWriter();

        try {
            Connection conn = DataSourceManager.getDataSource().getConnection();
            ResultSet rs = conn.prepareStatement("SELECT * FROM utente;").executeQuery();
            rs.next();
            out.println("success " + rs.getString(3));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (NamingException e) {
            throw new RuntimeException(e);
        }

        out.println("error");
    }

    public void destroy() {
    }
}