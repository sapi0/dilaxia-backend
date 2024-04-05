package com.sapi0.dilaxiabackend.data.impl;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public abstract class DaoImpl {

    protected Connection conn;

    public DaoImpl() throws NamingException, SQLException {
        conn = ((DataSource) new InitialContext().lookup("java:/comp/env/jdbc/dilaxia")).getConnection();
    }

    protected abstract void close() throws SQLException;   // FORSE

}
