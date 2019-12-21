package kieltenharjoitteluohjelma.dao;

import java.util.ArrayList;
import java.util.List;
import kieltenharjoitteluohjelma.domain.User;
import java.util.*;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import kieltenharjoitteluohjelma.domain.Language;

/**
 * UserDao-rajapinnan toteuttava luokka.
 */
public class FileUserDao implements UserDao {

    private DatabaseConnection connect;

    public FileUserDao(DatabaseConnection connect) {
        this.connect = connect;
    }

    /**
     * Käyttäjän tietokannasta hakeva ja palauttava metodi.
     *
     * @param username Käyttäjänimi, jonka perusteella käyttäjä haetaan.
     *
     * @return palauttaa User-olion. Jos tietokanta on tyhjä tai käyttäjää ei
     * löydy palauttaa null
     */
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

    /**
     * Käyttäjän salasanan tarkastava metodi.
     *
     * @param username Käyttäjän syötteenä antama käyttäjänimi
     * @param password Käyttäjän syötteenä antama salasana
     *
     * return false, jos käyttäjänimi ja salasana eivät vastaa toisiaan ja true,
     * jos ne vastaavat toisiaan
     */
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
            return null;
        }

        return false;
    }

    /**
     * Uuden käyttäjän tietokantaan lisäävä metodi
     *
     * @param user Käyttäjä-olio, jonka tiedot lisätään tietokantaan.
     */
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
