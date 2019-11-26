package kieltenharjoitteluohjelma.domain;

import java.util.HashMap;
import java.util.Random;
import java.util.TreeMap;

public class Kieltenharjoittelu {

    private HashMap<String, String> finEnglish;
    private HashMap<String, String> finSwedish;
    private HashMap<String, String> englishFin;
    private HashMap<String, String> swedishFin;
    private TreeMap<Integer, String> languages;

    public Kieltenharjoittelu() {
        this.finEnglish = new HashMap<>();
        this.finSwedish = new HashMap<>();
        this.englishFin = new HashMap<>();
        this.swedishFin = new HashMap<>();
        this.languages = new TreeMap<>();
        this.languages.put(1, "Englanti");
        this.languages.put(2, "Ruotsi");
    }

    public TreeMap<Integer, String> getLanguages() {
        return languages;
    }

    public void addWord(Integer language, String fin, String foreign) {
        if (language == 1) {
            finEnglish.put(fin, foreign);
            englishFin.put(foreign, fin);
        }
        if (language == 2) {
            finSwedish.put(fin, foreign);
            swedishFin.put(foreign, fin);
        }
    }

    public String practiseFinForFirst(int language) {
        HashMap<String, String> words = new HashMap<>();
        if (language == 1) {
            words = finEnglish;
        }
        if (language == 2) {
            words = finSwedish;
        }

        return "kissa";
    }

    public String practiseForFinFirst(int language) {
        HashMap<String, String> words = new HashMap<>();
        if (language == 1) {
            words = englishFin;
        }
        if (language == 2) {
            words = swedishFin;
        }

        return "kissa";
    }

    public String practiseFinForSec(int language, String word, String answer) {
        HashMap<String, String> words = new HashMap<>();
        if (language == 1) {
            words = finEnglish;
        }
        if (language == 2) {
            words = finSwedish;
        }

        String correctAnswer = "";

        if (words.containsKey(word)) {
            correctAnswer = words.get(word);
        }

        if (correctAnswer.equals(answer)) {
            return "Oikein!";
        }

        return "Väärin. Oikea vastaus on:" + correctAnswer;
    }

    public int pickRandomKey(int language) {
        int size = 0;

        if (language == 1) {
            size = finEnglish.size();
        }

        if (language == 2) {
            size = finSwedish.size();
        }

        Random random = new Random();

        return random.nextInt(size);
    }

}
