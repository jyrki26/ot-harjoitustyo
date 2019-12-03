package kieltenharjoitteluohjelma.dao;

import java.util.List;
import kieltenharjoitteluohjelma.domain.User;

public interface UserDao {

    List<User> getAll();

    Boolean findByUsername(String username, String password);

    User create(User user) throws Exception;

}
