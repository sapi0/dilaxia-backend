package com.sapi0.dilaxiabackend.utils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class NullableResultSet {

    private ResultSet rs;

    public NullableResultSet(ResultSet rs) {
        this.rs = rs;
    }

    public Integer getInt(String columnName) {
        try {
            return rs.getInt(columnName);
        } catch(SQLException e) {
            return null;
        }
    }

    public Boolean getBoolean(String columnName) {
        try {
            return rs.getBoolean(columnName);
        } catch(SQLException e) {
            return null;
        }
    }

    public String getString(String columnName) {
        try {
            return rs.getString(columnName);
        } catch(SQLException e) {
            return null;
        }
    }

    public Timestamp getTimestamp(String columnName) {
        try {
            return rs.getTimestamp(columnName);
        } catch(SQLException e) {
            return null;
        }
    }

    public ResultSet getResultSet() {
        return rs;
    }

    public void close() throws SQLException {
        rs.close();
    }

}
