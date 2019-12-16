package kieltenharjoitteluohjelma.dao;

import java.util.ArrayList;
import java.util.List;
import kieltenharjoitteluohjelma.domain.User;
import java.util.*;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FileUserDao implements UserDao {
    
    private Connect connect;

    public FileUserDao() {
        connect = new Connect();
    }
    

    @Override
    public User findByUsername(String username) throws SQLException {
        Connection connection = connect.connect();

        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Users WHERE NAME = ?");
        stmt.setString(1, username);

        ResultSet rs = stmt.executeQuery();

        if (!rs.next()) {
            return null;
        }

        User user = new User(rs.getString("name"), rs.getString("password"));

        stmt.close();
        rs.close();
        connection.close();

        return user;
    }

    @Override
    public Boolean checkPassword(String username, String password) {
        try {
            User user = findByUsername(username);

            if (user == null) {
                return false;
            }

            if (user.getName().equals(username) && user.getPassword().equals(password)) {
                return true;
            }
        } catch (SQLException ex) {
            System.out.println("Tietokantayhteydessä on ongelma. Yritä uudestaan myöhemmin." +ex);;
        }

        return false;
    }

    @Override
    public void create(User user) throws SQLException {
        Connection connection = connect.connect();

        PreparedStatement stmt = connection.prepareStatement("INSERT INTO Users"
                + " (id, name, password)"
                + " VALUES ($next_id, ?, ?)");
        stmt.setString(2, user.getName());
        stmt.setString(3, user.getPassword());
        stmt.executeUpdate();
        stmt.close();
        connection.close();
    }
}
