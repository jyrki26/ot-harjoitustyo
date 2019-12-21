package kieltenharjoitteluohjelma.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Luokka luo yhteyden SQL-tietokantaan.
 */
public class Connect implements DatabaseConnection {

    /**
     * Metodi palauttaa yhteyden SQL-tietokantaan.
     * 
     * @return Palauttaa yhteyden Connect-muodossa.
     */
    
    @Override
    public Connection connect() {
        Connection conn = null;
        try {
            String url = "jdbc:sqlite:database";

            conn = DriverManager.getConnection(url);

        } catch (SQLException e) {
        }

        return conn;
    }

}
