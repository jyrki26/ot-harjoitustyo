package kieltenharjoitteluohjelma.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connect {

    public Connection connect() {
        Connection conn = null;
        try {
            String url = "jdbc:sqlite:database";

            conn = DriverManager.getConnection(url);

            System.out.println("Connection to SQLite has been established.");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        
        return conn;
    }

}

