
package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import kieltenharjoitteluohjelma.dao.DatabaseConnection;


public class TestConnection implements DatabaseConnection {
    @Override
    public Connection connect() {
        Connection conn = null;
        try {
            String url = "jdbc:sqlite:testdatabase";

            conn = DriverManager.getConnection(url);

        } catch (SQLException e) {
        }

        return conn;
    }
}
