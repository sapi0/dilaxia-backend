package com.sapi0.dilaxiabackend;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;

public class DataSourceManager {

    private static DataSource ds;

    public static DataSource getDataSource() throws NamingException {
        if(ds == null) ds = createDataSource();
        return ds;
    }

    private static DataSource createDataSource() throws NamingException {
        InitialContext initContext = new InitialContext();
        DataSource envContext  = (DataSource)initContext.lookup("java:/comp/env/jdbc/dilaxia");
        return envContext;
    }

}
