package domain;

import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;
import junit.framework.Assert;
import kieltenharjoitteluohjelma.dao.FileLanguageDao;
import kieltenharjoitteluohjelma.dao.FileUserDao;
import kieltenharjoitteluohjelma.dao.LanguageDao;
import kieltenharjoitteluohjelma.dao.UserDao;
import kieltenharjoitteluohjelma.domain.KieltenharjoitteluService;
import kieltenharjoitteluohjelma.domain.User;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class KieltenharjoitteluServiceTest {
    
    KieltenharjoitteluService service;
    UserDao userDao;
    LanguageDao languageDao;
    
    @Before
    public void setUp() throws SQLException {
        userDao = new FakeUserDao();
        languageDao = new FakeLanguageDao();
        service = new KieltenharjoitteluService(userDao, languageDao);
        service.setLanguage(1);
        service.WordsFromDatabase();
    }
    
    @Test
    public void FinForFirstWorks() {
        service.practiseFinForFirst();
        assertEquals("pallo", service.getWordToTranslate());
    }
    
    @Test
    public void ForFinFirstWorks() {
        service.practiseForFinFirst();
        assertEquals("ball", service.getWordToTranslate());
    }
    
    @Test
    public void practiseFinForCorrectAnswer() {
        service.practiseFinForFirst();
        assertEquals("ball", service.getCorrectAnswer());
        
    }
    
    @Test
    public void practiseForFinCorrectAnswer() {
        service.practiseForFinFirst();
        assertEquals("pallo", service.getCorrectAnswer());
        
    }
    
    @Test
    public void addWordWorks() {
        assertTrue(service.addWord("kissa", "cat"));
    }
    
    @Test
    public void correctPassword() throws SQLException {
        assertTrue(service.passwordCorrect("hello", "world"));
    }
    
    @Test
    public void falsePassword() throws SQLException {
        assertFalse(service.passwordCorrect("hel", "world"));
    }
    
    @Test
    public void createUserWorks() throws SQLException {
        service.createUser("testi", "testi");
        assertTrue(service.passwordCorrect("testi", "testi"));
    }
    
    @Test
    public void createUserDuplicate() throws SQLException {
        assertFalse(service.createUser("hello", "afad"));
    }
    
    @Test
    public void loginWorks() throws SQLException{
        service.passwordCorrect("hello", "world");
        User u = service.getLoggedIn();
        assertEquals("hello", u.getName());
    }
    
    @Test
    public void logOutWorks() throws SQLException{
        service.passwordCorrect("hello", "world");
        User u = service.getLoggedIn();
        service.logout();
        assertTrue(null == service.getLoggedIn());
    }
    
    @Test
    public void practiseSecondWorksFin(){
        service.practiseFinForFirst();
        assertTrue(service.practiseSecond("ball"));
    }
    
    @Test
    public void practiseSecondWorksFor(){
        service.practiseForFinFirst();
        assertTrue(service.practiseSecond("pallo"));
    }
}
