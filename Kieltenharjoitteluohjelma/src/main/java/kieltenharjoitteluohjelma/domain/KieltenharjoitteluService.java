package kieltenharjoitteluohjelma.domain;

import java.util.ArrayList;
import java.util.List;
import kieltenharjoitteluohjelma.dao.UserDao;

public class KieltenharjoitteluService {

    private UserDao userDao;
    private List<User> users;

    public KieltenharjoitteluService(UserDao userDao) {
        this.userDao = userDao;
        this.users = new ArrayList<>();
        this.users.add(new User("hello", "world"));
    }

    public boolean createUser(String name, String password) {
        if (userDao.findByUsername(name, password) != null) {
            return false;
        }
        User user = new User(name, password);
        try {
            userDao.create(user);
        } catch (Exception e) {
            return false;
        }

        return true;
    }

    public Boolean passwordCorrect(String username, String password) {
        for (User user : users) {
            if (userDao.findByUsername(username, password)) {
                return true;
            }
        }
        return false;
    }

}
