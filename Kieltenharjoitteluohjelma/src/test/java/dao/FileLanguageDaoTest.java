
package dao;

import java.sql.SQLException;
import java.util.HashMap;
import kieltenharjoitteluohjelma.dao.FileLanguageDao;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;


public class FileLanguageDaoTest {
    
    FileLanguageDao languageDao;
    
    @Before
    public void setUp() {
        languageDao = new FileLanguageDao();
    }
    
    @Test
    public void englishWords() throws SQLException{
        HashMap<String, String> words =languageDao.words(1);
        assertTrue(!words.isEmpty());
        assertTrue(words.containsKey("pallo"));
        assertEquals("ball", words.get("pallo"));
    }
    
    @Test
    public void swedishWords() throws SQLException{
        HashMap<String, String> words =languageDao.words(2);
        assertTrue(!words.isEmpty());
        assertTrue(words.containsKey("pallo"));
        assertEquals("en boll", words.get("pallo"));
    }
}
