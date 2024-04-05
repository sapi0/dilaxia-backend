package com.sapi0.dilaxiabackend.data;

import com.sapi0.dilaxiabackend.data.impl.DaoImpl;
import com.sapi0.dilaxiabackend.data.impl.UserDaoImpl;

import javax.naming.NamingException;
import java.sql.SQLException;

public class DaoFactory {

    public static final DaoFactory instance = new DaoFactory();

    private UserDaoImpl userDao;

    private DaoFactory() {
        try {
            userDao = new UserDaoImpl();
        } catch (NamingException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public UserDaoImpl getUserDao() {
        return userDao;
    }

}
