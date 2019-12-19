
package domain;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import kieltenharjoitteluohjelma.dao.UserDao;
import kieltenharjoitteluohjelma.domain.User;


public class FakeUserDao implements UserDao {
    
    List<User> users = new ArrayList<>();

    public FakeUserDao() {
       this.users.add(new User("hello", "world"));
    }
    
    

    @Override
    public User findByUsername(String username) throws SQLException {
        User user;
        for(User u: this.users){
            if(u.getName().equals(username)){
                user = u;
                return user;
            }
        }
        return null;
    }

    @Override
    public void create(User user) throws SQLException {
        this.users.add(user);
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
        }

        return false;
    }
    
}
