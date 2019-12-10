package testPackage;

import java.util.HashSet;
import java.util.Set;
import junit.framework.Assert;
import kieltenharjoitteluohjelma.dao.FileUserDao;
import kieltenharjoitteluohjelma.domain.KieltenharjoitteluService;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class KieltenharjoitteluServiceTest {
    
    KieltenharjoitteluService service;
    FileUserDao userDao;
    
    @Before
    public void setUp() {
        userDao = new FileUserDao();
        service = new KieltenharjoitteluService(userDao);
        service.setLanguage(1);
    }
    
    @Test
    public void FinForFirstWorks() {
        Set<String> possibleAnswers = new HashSet<>();
        possibleAnswers.add("kissa");
        possibleAnswers.add("koira");
        possibleAnswers.add("lintu");
        
        service.practiseFinForFirst();
        
        assertTrue(possibleAnswers.contains(service.getWordToTranslate()));
    }
    
    @Test
    public void ForFinFirstWorks() {
        Set<String> possibleAnswers = new HashSet<>();
        possibleAnswers.add("cat");
        possibleAnswers.add("dog");
        possibleAnswers.add("bird");
        
        service.practiseForFinFirst();
        
        assertTrue(possibleAnswers.contains(service.getWordToTranslate()));
    }
    
    @Test
    public void practiseFinForCorrectAnswer() {
        service.practiseForFinFirst();
        String word = service.getWordToTranslate();
        String correct = "";
        if (word.equals("kissa")) {
            correct = "cat";
        }
        if (word.equals("koira")) {
            correct = "dog";
        }
        if (word.equals("lintu")) {
            correct = "bird";
        }
        
    }
    
    @Test
    public void addWordWorks() {
        assertTrue(service.addWord("pallo", "boll"));
    }
    
    @Test
    public void addWordSwedishWorks() {
        service.setLanguage(2);
        assertTrue(service.addWord("pallo", "boll"));
    }
    
    @Test
    public void correctPassword() {
        assertTrue(service.passwordCorrect("hello", "world"));
    }
    
    @Test
    public void falsePassword() {
        assertFalse(service.passwordCorrect("hel", "world"));
    }
    
    public void createUserWorks() {
        service.createUser("testi", "testi");
        assertTrue(service.passwordCorrect("testi", "testi"));
    }
}
