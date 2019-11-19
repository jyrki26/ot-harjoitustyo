package testPackage;

import java.util.Scanner;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import kieltenharjoitteluohjelma.ui.UserInterface;
import kieltenharjoitteluohjelma.domain.Kieltenharjoittelu;


public class UserInterfaceTest {
    
       Scanner scanner;
       Kieltenharjoittelu domain;
       UserInterface ui;
       
    
    @Before
    public void setUp() {
        scanner = new Scanner(System.in);
        domain = new Kieltenharjoittelu();
        ui = new UserInterface(scanner, domain);
    }
    
    @Test
    public void addignNewWordWorks(){
    }

    
}
