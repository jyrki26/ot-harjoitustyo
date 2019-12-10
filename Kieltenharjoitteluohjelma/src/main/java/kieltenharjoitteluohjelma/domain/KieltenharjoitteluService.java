package kieltenharjoitteluohjelma.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;
import kieltenharjoitteluohjelma.dao.UserDao;

public class KieltenharjoitteluService {

    private UserDao userDao;
    private List<User> users;
    private TreeMap<Integer, String> languages;
    private Language english;
    private Language swedish;
    private int language;
    private String wordToTranslate;

    public KieltenharjoitteluService(UserDao userDao) {
        this.userDao = userDao;
        this.language = 0;
        this.languages = new TreeMap<>();
        this.users = new ArrayList<>();
        this.users.add(new User("hello", "world"));
        this.english = new Language("Englanti");
        this.swedish = new Language("Ruotsi");
        this.languages.put(1, "Englanti");
        this.languages.put(2, "Ruotsi");
        this.wordToTranslate = "";

    }

    public TreeMap<Integer, String> getLanguages() {
        return languages;
    }

    public boolean createUser(String name, String password) {
        if (userDao.findByUsername(name, password) != null) {
            return false;
        }
        User user = new User(name, password);
        try {
            userDao.create(user);
        } catch (Exception e) {
            return false;
        }

        return true;
    }

    public Boolean passwordCorrect(String username, String password) {
        for (User user : users) {
            if (userDao.findByUsername(username, password)) {
                return true;
            }
        }
        return false;
    }

    public Boolean addWord(String fin, String foreign) {
        if (language == 1) {
            try {
                english.addWord(fin, foreign);
            } catch (Exception ex) {
                return false;
            }
        }
        if (language == 2) {
            try {
                swedish.addWord(fin, foreign);
            } catch (Exception ex) {
                return false;
            }
        }
        return true;
    }

    public void practiseForFinFirst() {
        if (language == 1) {
            wordToTranslate = english.randomFor();
        }
        if (language == 2) {
            wordToTranslate = swedish.randomFor();
        }
    }

    public void practiseFinForFirst() {
        if (language == 1) {
            wordToTranslate = english.randomFin();
        }
        if (language == 2) {
            wordToTranslate = swedish.randomFin();
        }
    }

    public String practiseFinForSec(String answer) {
        String correctAnswer = "";
        if (language == 1) {
            correctAnswer = english.translationFinFor(wordToTranslate);
        }
        if (language == 2) {
            correctAnswer = swedish.translationFinFor(wordToTranslate);
        }

        if (correctAnswer.equals(answer)) {
            return "Oikein!";
        }

        return "Väärin. Oikea vastaus on: " + correctAnswer;
    }

    public void setLanguage(int lang) {
        this.language = lang;
    }

    public String getWordToTranslate() {
        return wordToTranslate;
    }

}
