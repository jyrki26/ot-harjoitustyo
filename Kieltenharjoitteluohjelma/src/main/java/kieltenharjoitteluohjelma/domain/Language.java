package kieltenharjoitteluohjelma.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.TreeMap;

/**
 * Harjoiteltavaa kieltä kuvaava luokka.
 */
public class Language {

    public HashMap<String, String> finFor;
    public HashMap<String, String> forFin;

    public Language() {
        this.finFor = new HashMap<>();
        this.forFin = new HashMap<>();

    }

    /**
     * Metodin avulla asetetaan sanat niin, että key on vieraskielinen ja value
     * on suomenkielinen sana.
     *
     * @param finFor HashMap, jossa sanat ovat.
     *
     */
    public void setFinFor(HashMap<String, String> finFor) {
        this.finFor = finFor;
    }

    /**
     * Metodin avulla asetetaan sanat niin, että key on suomenkielinen ja value
     * on vieraskielinen sana.
     *
     * @param forFin HashMap, jossa sanat ovat.
     *
     */
    public void setForFin(HashMap<String, String> forFin) {
        this.forFin = forFin;
    }

    /**
     * Metodin avulla lisätään sanapari harjoittelua varten.
     *
     * @param fin suomenkielinen sana
     * @param foreign vieraskielinen sana
     *
     */
    public void addWord(String fin, String foreign) {
        finFor.put(fin, foreign);
        forFin.put(foreign, fin);
    }

    /**
     * Metodi arpoo satunnaisen suomenkielisen sanan.
     *
     * @return Palauttaa käännöksen String-muodossa.
     *
     */
    public String randomFin() {
        ArrayList<String> words = new ArrayList<>(finFor.keySet());
        int random = new Random().nextInt(words.size());
        return words.get(random);
    }

    /**
     * Metodi arpoo satunnaisen vieraskielisen sanan.
     *
     * @return Palauttaa sanan String-muodossa.
     *
     */
    public String randomFor() {
        ArrayList<String> words = new ArrayList<>(forFin.keySet());
        int random = new Random().nextInt(words.size());
        return words.get(random);
    }

    /**
     * Metodi antaa käännöksen suomenkieliselle sanalle.
     *
     * @return Palauttaa käännöksen String-muodossa.
     *
     */
    public String translationFinFor(String word) {
        return finFor.get(word);
    }

    /**
     * Metodi antaa käännöksen vieraskieliselle sanalla.
     *
     * @return Palauttaa käännöksen String-muodossa.
     *
     */
    public String translationForFin(String word) {
        return forFin.get(word);
    }

}
