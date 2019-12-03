package kieltenharjoitteluohjelma.dao;

import java.util.ArrayList;
import java.util.List;
import kieltenharjoitteluohjelma.domain.User;

public class FileUserDao implements UserDao {

    private List<User> users;

    public FileUserDao() {
        this.users = new ArrayList<>();
        this.users.add(new User("hello", "world"));
    }

    @Override
    public List<User> getAll() {
        return users;
    }

    @Override
    public Boolean findByUsername(String username, String password) {
        for (User user : users) {
            if (user.getName().equals(username)) {
                if (user.getPassword().equals(password)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public User create(User user) throws Exception {
        users.add(user);
        return user;
    }
}
