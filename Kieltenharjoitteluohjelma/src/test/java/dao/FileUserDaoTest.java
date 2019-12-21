package dao;

import java.sql.SQLException;
import kieltenharjoitteluohjelma.dao.FileUserDao;
import kieltenharjoitteluohjelma.domain.User;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

public class FileUserDaoTest {

    FileUserDao userDao;
    TestConnection connect;

    @Before
    public void setUp() {
        this.connect = new TestConnection();
        userDao = new FileUserDao(connect);
    }

    @Test
    public void findByUserNameWorks() throws SQLException {
        User user = userDao.findByUsername("hello");
        assertEquals(user.getName(), "hello");
    }

    @Test
    public void nonExistingUser() throws SQLException {
        User user = userDao.findByUsername("a");
        assertTrue(user == null);
    }

    @Test
    public void correctPassword() {
        assertTrue(userDao.checkPassword("hello", "world"));
    }

    @Test
    public void falsePassword() {
        assertFalse(userDao.checkPassword("hello", "wor"));
    }

    @Test
    public void falseUserName() {
        assertFalse(userDao.checkPassword("h", "world"));
    }

}
