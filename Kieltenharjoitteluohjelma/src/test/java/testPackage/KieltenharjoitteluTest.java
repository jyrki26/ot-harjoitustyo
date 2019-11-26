package testPackage;

import java.util.HashMap;
import java.util.TreeMap;
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
    public void getLanguagesWork() {
        TreeMap<Integer, String> languages = domain.getLanguages();
        assertEquals("Englanti", languages.get(1));
    }

    @Test
    public void addWordWorks() {
        domain.addWord(1, "kissa", "cat");
    }

    @Test
    public void practiseFinEngWorks() {
        String word = domain.practiseFinForFirst(1);
        assertEquals("kissa", word);
    }

    @Test
    public void practiseFinSweWorks() {
        String word = domain.practiseFinForFirst(2);
        assertEquals("kissa", word);
    }

    @Test
    public void practiseEngFinWorks() {
        String word = domain.practiseForFinFirst(1);
        assertEquals("kissa", word);
    }

    @Test
    public void practiseSweFinWorks() {
        String word = domain.practiseForFinFirst(2);
        assertEquals("kissa", word);
    }

}
