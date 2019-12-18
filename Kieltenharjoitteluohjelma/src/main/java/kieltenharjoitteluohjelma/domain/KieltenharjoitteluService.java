package kieltenharjoitteluohjelma.domain;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;
import kieltenharjoitteluohjelma.dao.LanguageDao;
import kieltenharjoitteluohjelma.dao.UserDao;

public class KieltenharjoitteluService {

    private UserDao userDao;
    private LanguageDao languageDao;
    private TreeMap<Integer, String> languages;
    private Language language;
    private int languageInt;
    private String wordToTranslate;
    private User loggedIn;

    public KieltenharjoitteluService(UserDao userDao, LanguageDao languageDao) {
        this.userDao = userDao;
        this.languageDao = languageDao;
        this.languageInt = 0;
        this.languages = new TreeMap<>();
        this.language = new Language();
        this.languages.put(1, "Englanti");
        this.languages.put(2, "Ruotsi");
        this.wordToTranslate = "";

    }

    public TreeMap<Integer, String> getLanguages() {
        return languages;
    }

    public Boolean createUser(String name, String password) {
        try {
            if (userDao.findByUsername(name) != null) {
                return false;
            }
        } catch (SQLException ex) {
            return null;
        }
        User user = new User(name, password);
        try {
            userDao.create(user);
        } catch (SQLException ex) {
            return null;
        }

        return true;
    }

    public Boolean passwordCorrect(String username, String password) throws SQLException {
        if (userDao.checkPassword(username, password)) {
            loggedIn = userDao.findByUsername(username);
            return true;
        }
        return false;
    }
    
    public User getLoggedIn(){
        return loggedIn;
    }
    
    public void logout(){
        loggedIn = null;
    }

    public Boolean addWord(String fin, String foreign) {
        try {
            languageDao.addWord(languageInt, fin, foreign);
            language.addWord(fin, foreign);
        } catch (Exception ex) {
            return false;
        }
        return true;
    }

    public void practiseForFinFirst() {
        wordToTranslate = language.randomFor();
    }

    public void practiseFinForFirst() {
        wordToTranslate = language.randomFin();
    }

    public Boolean practiseFinForSec(String answer) {
        String correctAnswer = language.translationFinFor(wordToTranslate);
        if (answer.equals(correctAnswer)) {
            return true;
        }

        return false;
    }
    
    public String getCorrectAnswer(){
        String correctAnswer = language.translationFinFor(wordToTranslate);
        return correctAnswer;
    }

    public void setLanguage(int lang) {
        this.languageInt = lang;
    }

    public String getWordToTranslate() {
        return wordToTranslate;
    }

    public void WordsFromDatabase() throws SQLException {
        HashMap<String, String> words = languageDao.words(languageInt);
        language.setFinFor(words);

        HashMap<String, String> forFinWords = new HashMap<>();
        for (String word : words.keySet()) {
            forFinWords.put(words.get(word), word);
        }

        language.setForFin(forFinWords);
    }

}
