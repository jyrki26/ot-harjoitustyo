
package kieltenharjoitteluohjelma.ui;
import kieltenharjoitteluohjelma.domain.Kieltenharjoittelu;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Kieltenharjoittelu domain = new Kieltenharjoittelu();
        UserInterface ui = new UserInterface(scanner, domain);
        ui.start();
        
    }
}
