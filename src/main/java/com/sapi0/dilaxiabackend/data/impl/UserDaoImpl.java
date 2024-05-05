package com.sapi0.dilaxiabackend.data.impl;

import com.sapi0.dilaxiabackend.data.entity.Event;
import com.sapi0.dilaxiabackend.data.entity.User;
import com.sapi0.dilaxiabackend.data.dao.IUserDao;
import com.sapi0.dilaxiabackend.utils.NullableResultSet;

import javax.naming.NamingException;
import javax.xml.transform.Result;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl extends DaoImpl implements IUserDao {

    private static final String TABLE_NAME = "user";

    private PreparedStatement count, getUserByID, getUserByEmail, addUser, updateUserByID, deleteUserByID, research;

    public UserDaoImpl() throws NamingException, SQLException {
        super();    // necessario
    }

    protected void initStatements() throws SQLException {
        count = conn.prepareStatement("SELECT COUNT(id) FROM " + TABLE_NAME);
        getUserByID = conn.prepareStatement(
                "SELECT id, name, surname, email, type, created " +
                    "FROM " + TABLE_NAME + " " +
                    "WHERE id = ?"
        );
        getUserByEmail = conn.prepareStatement(
                "SELECT id, name, surname, email, hash, type, created " +
                    "FROM " + TABLE_NAME + " " +
                    "WHERE email = ?"
        );
        addUser = conn.prepareStatement(
                "INSERT INTO " + TABLE_NAME + "(" +
                        "name, surname, email, hash, type" +
                    ") VALUES (?, ?, ?, ?, ?)"
        );
        updateUserByID = conn.prepareStatement(
                "UPDATE " + TABLE_NAME + " " +
                    "SET name = ?, surname = ? " +
                    "WHERE id = ?"
        );
        deleteUserByID = conn.prepareStatement(
                "DELETE FROM " + TABLE_NAME + " " +
                    "WHERE id = ?"
        );
        research = conn.prepareStatement(
                "SELECT id, name, surname, email, hash, type, created " +
                    "FROM " + TABLE_NAME + " " +
                    "LIMIT ? OFFSET ?"
        );
    }

    private User createUser(ResultSet rset) throws SQLException {
        NullableResultSet rs = new NullableResultSet(rset);

        Integer id = rs.getInt("id");
        String name = rs.getString("name");
        String surname = rs.getString("surname");
        String email = rs.getString("email");
        String hash = rs.getString("hash");
        Integer type = rs.getInt("type");
        Timestamp created = rs.getTimestamp("created");

        return new User(id, name, surname, email, hash, type, created);
    }

    @Override
    public int count() throws SQLException {
        ResultSet rs = count.executeQuery();

        if(rs.next()) {
            int result = rs.getInt(1);
            rs.close();
            return result;
        }

        rs.close();
        return -1;
    }

    @Override
    public List<User> research(int pageSize, int page) throws SQLException {
        research.setInt(1, pageSize);
        research.setInt(2, (page-1)*pageSize);

        ResultSet rs = research.executeQuery();
        List<User> users = new ArrayList<>();

        while(rs.next()){
            users.add(createUser(rs));
        }

        rs.close(); // TODO mettere il close a tutti
        return users;
    }

    @Override
    public User get(int id) throws SQLException {
        getUserByID.setInt(1, id);
        ResultSet rs = getUserByID.executeQuery();

        if(rs.next()) {
            User result = createUser(rs);
            rs.close();
            return result;
        }

        rs.close();
        return null;
    }

    @Override
    public User get(String email) throws SQLException {
        getUserByEmail.setString(1, email);
        ResultSet rs = getUserByEmail.executeQuery();

        if(rs.next()) {
            User result = createUser(rs);
            rs.close();
            return result;
        }

        rs.close();
        return null;
    }

    @Override
    public void add(User user) throws SQLException {
        addUser.setString(1, user.getName());
        addUser.setString(2, user.getSurname());
        addUser.setString(3, user.getEmail());
        addUser.setString(4, user.getHash());
        addUser.setInt(5, user.getType());

        addUser.execute();
    }

    @Override
    public void update(int id, User user) throws SQLException {
        updateUserByID.setString(1, user.getName());
        updateUserByID.setString(2, user.getSurname());

        updateUserByID.setInt(3, id);

        updateUserByID.execute();
    }

    @Override
    public void delete(int id) throws SQLException {
        deleteUserByID.setInt(1, id);

        updateUserByID.execute();
    }

    @Override
    public void close() throws SQLException {
        count.close();
        getUserByID.close();
        getUserByEmail.close();
        addUser.close();
        updateUserByID.close();
        deleteUserByID.close();
        research.close();
    }

}
