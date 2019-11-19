package testPackage;

import java.util.HashMap;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import kieltenharjoitteluohjelma.domain.Kieltenharjoittelu;


public class KieltenharjoitteluTest {
    
    Kieltenharjoittelu domain;
    
    @Before
    public void setUp() {
        domain = new Kieltenharjoittelu();
    }

    @Test
    public void addWordKeyWorks(){
        domain.addWord(1, "kissa", "cat");
        HashMap<String, String> words = domain.getLanguages().get(1);
        assertTrue(words.containsKey("kissa"));
    }
    
    @Test
    public void addWordValueWorks(){
        domain.addWord(1, "kissa", "cat");
        HashMap<String, String> words = domain.getLanguages().get(1);
        assertEquals("cat", words.get("kissa"));
    }
}
