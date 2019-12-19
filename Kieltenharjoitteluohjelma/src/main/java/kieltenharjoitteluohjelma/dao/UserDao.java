package kieltenharjoitteluohjelma.dao;

import kieltenharjoitteluohjelma.domain.User;
import java.util.*;
import java.sql.*;

public interface UserDao {

    User findByUsername(String username) throws SQLException;

    void create(User user) throws SQLException;
    
    Boolean checkPassword(String username, String password);

}
