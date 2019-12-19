
package dao;

import java.sql.SQLException;
import kieltenharjoitteluohjelma.dao.FileUserDao;
import kieltenharjoitteluohjelma.domain.User;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

public class FileUserDaoTest {
    
    FileUserDao userDao;
    
    @Before
    public void setUp() {
        userDao = new FileUserDao();
    }
    
    @Test
    public void findByUserNameWorks() throws SQLException{
        User user = userDao.findByUsername("hello");
        assertEquals(user.getName(), "hello");
    }
    
    @Test
    public void nonExistingUser() throws SQLException{
        User user = userDao.findByUsername("a");
        assertTrue(user == null);
    }
    
    @Test
    public void correctPassword(){
        assertTrue(userDao.checkPassword("hello", "world"));
    }
    
    @Test
    public void falsePassword(){
        assertFalse(userDao.checkPassword("hello", "wor"));
    }
    
    @Test
    public void falseUserName(){
        assertFalse(userDao.checkPassword("h", "world"));
    }
    
}
