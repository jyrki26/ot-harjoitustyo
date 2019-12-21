package domain;

import kieltenharjoitteluohjelma.domain.Language;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

public class LanguageTest {

    Language language;

    @Before
    public void setUp() {
        language = new Language();
        language.addWord("pallo", "boll");
    }

    @Test
    public void translationFinForWorks() {
        assertEquals("boll", language.translationFinFor("pallo"));
    }

    @Test
    public void translationForFinWorks() {
        assertEquals("pallo", language.translationForFin("boll"));
    }

    @Test
    public void addWordWorks() {
        language.addWord("kissa", "cat");
        assertEquals("cat", language.translationFinFor("kissa"));
        assertEquals("kissa", language.translationForFin("cat"));
    }

    @Test
    public void randomFinWorks() {
        assertEquals("pallo", language.randomFin());
    }

    @Test
    public void randomForWorks() {
        assertEquals("boll", language.randomFor());
    }
}
