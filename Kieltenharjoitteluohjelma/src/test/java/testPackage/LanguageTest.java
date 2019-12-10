package testPackage;


import kieltenharjoitteluohjelma.domain.Language;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;


public class LanguageTest {
    
   Language language;
   
   @Before
    public void setUp() {
        language = new Language("Test");
    }
    
    @Test
    public void translationFinForWorks(){
        assertEquals("cat", language.translationFinFor("kissa"));
    }
}
