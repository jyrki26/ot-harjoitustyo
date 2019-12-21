package dao;

import java.sql.SQLException;
import java.util.HashMap;
import kieltenharjoitteluohjelma.dao.Connect;
import kieltenharjoitteluohjelma.dao.DatabaseConnection;
import kieltenharjoitteluohjelma.dao.FileLanguageDao;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

public class FileLanguageDaoTest {

    FileLanguageDao languageDao;
    DatabaseConnection connect;

    @Before
    public void setUp() {
        this.connect = new TestConnection();
        languageDao = new FileLanguageDao(connect);
    }

    @Test
    public void englishWords() throws SQLException {
        HashMap<String, String> words = languageDao.words(1);
        assertTrue(!words.isEmpty());
        assertTrue(words.containsKey("pallo"));
        assertEquals("ball", words.get("pallo"));
    }

    @Test
    public void swedishWords() throws SQLException {
        HashMap<String, String> words = languageDao.words(2);
        assertTrue(!words.isEmpty());
        assertTrue(words.containsKey("pallo"));
        assertEquals("en boll", words.get("pallo"));
    }
}
