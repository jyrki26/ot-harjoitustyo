package kieltenharjoitteluohjelma.ui;

import java.util.HashMap;
import kieltenharjoitteluohjelma.domain.Kieltenharjoittelu;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class UserInterface {

    Scanner scanner;
    Kieltenharjoittelu domain;
    private Map<String, String> startingCommands;
    private Map<String, String> commands;
    private Map<Integer, HashMap> languages;

    public UserInterface(Scanner scanner, Kieltenharjoittelu domain) {
        this.scanner = scanner;
        this.domain = domain;
        this.startingCommands = new TreeMap<>();
        this.commands = new TreeMap<>();
        this.languages = domain.getLanguages();

        startingCommands.put("0", "Lopeta");
        startingCommands.put("1", "Englanti");
        startingCommands.put("2", "Ruotsi");

        commands.put("1", "Lisää sanapari");
        commands.put("2", "Harjoittelu: Suomi-Vieras kieli");
        commands.put("3", "Harjoittelu: Vieras kieli - Suomi");
    }

    public void start() {
        System.out.println("Kieltenharjoitteluohjelma");
        while (true) {
            System.out.println();
            instructions();

            System.out.println("Komento:");
            String command = scanner.nextLine();

            if (!startingCommands.keySet().contains(command)) {
                System.out.println("Virheellinen komento.");
                System.out.println("");
                instructions();
            }
            if (command.equals("0")) {
                break;
            }
            if (command.equals("1")) {
                english();
            }
            if (command.equals("2")) {
                swedish();
            }
        }
    }

    public void instructions() {
        System.out.println("Anna haluamaasi komentoa vastaava numero ja paina enter.");
        for (String number : startingCommands.keySet()) {
            System.out.println(number + " - " + startingCommands.get(number));
        }
    }

    public void trainingCommands(int language) {
        String command = scanner.nextLine();
        if (command.equals("1")) {
            addWord(language);
        }
        if (command.equals("2")) {

        }
        if (command.equals("3")) {

        }
    }

    public void english() {
        System.out.println("Anna haluamaasi komentoa vastaava numero ja paina enter.");
        for (String number : commands.keySet()) {
            System.out.println(number + " - " + commands.get(number));
        }
        trainingCommands(1);

    }

    public void swedish() {
        HashMap words = this.languages.get(2);
        System.out.println("Anna haluamaasi komentoa vastaava numero ja paina enter.");
        for (String number : commands.keySet()) {
            System.out.println(number + " - " + commands.get(number));
        }
        trainingCommands(2);
    }

    public void addWord(int language) {
        System.out.println("Anna sana suomeksi: ");
        String finnish = scanner.nextLine();
        System.out.println("Anna sana vieraalla kielellä: ");
        String foreignWord = scanner.nextLine();
        
        
        domain.addWord(language, finnish.toLowerCase(), foreignWord.toLowerCase());
        System.out.println("");
        System.out.println("Sanan lisääminen onnistui");
        System.out.println("");
        System.out.println("Haluatko lisätä uuden sanan vai palata alkuun?");
        System.out.println("1 = Lisää uusi sana");
        System.out.println("Muu merkki = Alkuun");
        String command = scanner.nextLine();
        if(command.equals("1")){
            addWord(language);
        }
    }

}
