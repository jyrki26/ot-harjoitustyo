
package testPackage;

import java.util.ArrayList;
import java.util.List;
import kieltenharjoitteluohjelma.dao.FileUserDao;
import kieltenharjoitteluohjelma.domain.User;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class UserDaoTest {
    
    FileUserDao userDao;
    
    @Before
    public void setUp() {
        userDao = new FileUserDao();
    }
    
    @Test
    public void findAllUsersWorks(){
        List<User> users = userDao.getAll();
    }
    
    @Test
    public void correctPassword(){
        assertTrue(userDao.findByUsername("hello", "world"));
    }
    
    @Test
    public void falseName(){
        assertFalse(userDao.findByUsername("helo", "world"));
    }
    
    @Test
    public void falsePassword(){
        assertFalse(userDao.findByUsername("hello", "testi"));
    }
    
}
