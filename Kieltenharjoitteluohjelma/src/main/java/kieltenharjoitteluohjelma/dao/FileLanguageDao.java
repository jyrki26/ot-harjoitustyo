package kieltenharjoitteluohjelma.dao;

import java.util.HashMap;
import java.util.*;
import java.sql.*;

public class FileLanguageDao implements LanguageDao {

    private Connect connect;

    public FileLanguageDao() {
        connect = new Connect();
    }

    @Override
    public HashMap<String, String> words(Integer language) throws SQLException {
        Connection connection = connect.connect();
        HashMap<String, String> words = new HashMap<>();

        PreparedStatement stmt = null;
        if (language == 1) {
            try {
                stmt = connection.prepareStatement("SELECT * FROM English");
            } catch (SQLException ex) {
                return null;
            }
        }
        if (language == 2) {
            try {
                stmt = connection.prepareStatement("SELECT * FROM Swedish");
            } catch (SQLException ex) {
                return null;
            }
        }
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            words.put(rs.getString("finnish_word"), rs.getString("foreign_word"));
        }
        connection.close();

        return words;

    }

    public boolean addWord(Integer language, String finnish, String foreign) throws SQLException {
        Connection connection = connect.connect();
        String lang = "Swedish";
        if (language == 1) {
            lang = "English";
        }
        PreparedStatement stmt = null;
        try {
            stmt = connection.prepareStatement("INSERT INTO " + lang
                    + " (id, finnish_word, foreign_word)"
                    + " VALUES ($next_id, ?, ?)");
            stmt.setString(2, finnish);
            stmt.setString(3, foreign);
            stmt.executeUpdate();
            connection.close();
            return true;
        } catch (SQLException ex) {
            System.out.println(ex);
            return false;
        }
    }
}
