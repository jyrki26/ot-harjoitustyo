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

}
