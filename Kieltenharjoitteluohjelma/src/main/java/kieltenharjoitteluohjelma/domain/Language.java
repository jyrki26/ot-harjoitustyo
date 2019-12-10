package kieltenharjoitteluohjelma.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.TreeMap;

public class Language {

    public String name;
    public HashMap<String, String> finFor;
    public HashMap<String, String> forFin;

    public Language(String name) {
        this.name = name;
        this.finFor = new HashMap<>();
        finFor.put("kissa", "cat");
        finFor.put("koira", "dog");
        finFor.put("lintu", "bird");
        this.forFin = new HashMap<>();
        forFin.put("cat", "kissa");
        forFin.put("dog", "koira");
        forFin.put("bird", "lintu");
    }

    public HashMap<String, String> getFinFor() {
        return finFor;
    }

    public HashMap<String, String> getForFin() {
        return forFin;
    }

    public void addWord(String fin, String foreign) {
        finFor.put(fin, foreign);
        forFin.put(foreign, fin);
    }

    public String randomFin() {
        ArrayList<String> words = new ArrayList<>(finFor.keySet());
        int random = new Random().nextInt(words.size());
        return words.get(random);
    }

    public String randomFor() {
        ArrayList<String> words = new ArrayList<>(forFin.keySet());
        int random = new Random().nextInt(words.size());
        return words.get(random);
    }

    public String translationFinFor(String word) {
        return finFor.get(word);
    }

    public String translationForFin(String word) {
        return forFin.get(word);
    }

}
