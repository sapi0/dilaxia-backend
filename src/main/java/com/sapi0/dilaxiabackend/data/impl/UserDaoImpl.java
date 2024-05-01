package com.sapi0.dilaxiabackend.data.impl;

import com.sapi0.dilaxiabackend.data.entity.User;
import com.sapi0.dilaxiabackend.data.dao.IUserDao;

import javax.naming.NamingException;
import javax.xml.transform.Result;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl extends DaoImpl implements IUserDao {

    private static final String TABLE_NAME = "user";

    private PreparedStatement count, getAllUsers, getUserByID, getUserByEmail, addUser, updateUserByID, deleteUserByID, research;

    public UserDaoImpl() throws NamingException, SQLException {
        super();    // necessario
        initStatements();
    }

    private void initStatements() throws SQLException {
        count = conn.prepareStatement("SELECT COUNT(id) FROM " + TABLE_NAME);
        getAllUsers = conn.prepareStatement("SELECT * FROM " + TABLE_NAME);
        getUserByID = conn.prepareStatement("SELECT * FROM " + TABLE_NAME + " WHERE id = ?");
        getUserByEmail = conn.prepareStatement("SELECT * FROM " + TABLE_NAME + " WHERE email = ?");
        addUser = conn.prepareStatement("INSERT INTO " + TABLE_NAME + "(name, surname, email, hash, type) VALUES (?, ?, ?, ?, ?)");
        updateUserByID = conn.prepareStatement("UPDATE " + TABLE_NAME + " SET id = ? WHERE id = ?");
        deleteUserByID = conn.prepareStatement("DELETE FROM " + TABLE_NAME + " WHERE id = ?");

        research = conn.prepareStatement("SELECT id, name, surname, email, hash, type, created FROM " + TABLE_NAME + " LIMIT ? OFFSET ?");
    }

    private User createUserFromResultSet(ResultSet rs) throws SQLException {
        int _id = rs.getInt(1);
        String name = rs.getString(2);
        String surname = rs.getString(3);
        String email = rs.getString(4);
        String hash = rs.getString(5);
        int type = rs.getInt(6);
        Timestamp created = rs.getTimestamp(7);

        return new User(_id, name, surname, email, hash, type, created);
    }

    @Override
    public int count() throws SQLException {
        ResultSet rs = count.executeQuery();

        if(rs.next()) {
            return rs.getInt(1);
        }

        return -1;
    }

    @Override
    public List<User> research(int page, int pageSize) throws SQLException {
        research.setInt(1, pageSize);
        research.setInt(2, (page-1)*pageSize);
        return null;
    }

    @Override
    public List<User> all() throws SQLException {
        ResultSet rs = getAllUsers.executeQuery();

        List<User> users = new ArrayList<>();

        while(rs.next())
            users.add(createUserFromResultSet(rs));

        return users;
    }

    @Override
    public User get(int id) throws SQLException {
        getUserByID.setInt(1, id);
        ResultSet rs = getUserByID.executeQuery();

        if(rs.next())
            return createUserFromResultSet(rs);

        return null;
    }

    @Override
    public User get(String email) throws SQLException {
        getUserByEmail.setString(1, email);
        ResultSet rs = getUserByEmail.executeQuery();

        if(rs.next())
            return createUserFromResultSet(rs);

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
    public void update(int id, User user) {

    }

    @Override
    public void delete(int id) {

    }

    @Override
    public void close() throws SQLException {
        getAllUsers.close();
        getUserByID.close();
        addUser.close();
        updateUserByID.close();
        deleteUserByID.close();
    }
}
