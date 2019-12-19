package kieltenharjoitteluohjelma.domain;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;
import kieltenharjoitteluohjelma.dao.LanguageDao;
import kieltenharjoitteluohjelma.dao.UserDao;

/**
 * Sovelluslogiikasta vastaava luokka.
 */
public class KieltenharjoitteluService {

    private UserDao userDao;
    private LanguageDao languageDao;
    private TreeMap<Integer, String> languages;
    private Language language;
    private int languageInt;
    private String wordToTranslate;
    private User loggedIn;
    private int direction;

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

    public int getDirection() {
        return direction;
    }
    
    

    /**
     * Metodin avulla luodaan uusi käyttäjä.
     *
     * @param name Käyttäjän antama syöte
     * @param password Käyttäjän antama syöte
     *
     * @see
     * kieltenharjoitteluohjelma.dao.UserDao#findByUsername(java.lang.String)
     *
     * @return false, jos samanniminen käyttäjä on jo olemassa ja true, jos
     * käyttäjän luominen onnistuu
     */
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

    /**
     * Metodi tarkastaa onko käyttäjän salasana oikein.
     *
     * @param username Käyttäjän antama syöte
     * @param password Käyttäjän antama syöte
     *
     * @see
     * kieltenharjoitteluohjelma.dao.UserDao#findByUsername(java.lang.String)
     *
     * @return false, jos käyttäjätunnus ja salasana eivät vastaa toisiaan ja
     * true, jos ne vastaavat toisiaan.
     */
    public Boolean passwordCorrect(String username, String password) throws SQLException {
        if (userDao.checkPassword(username, password)) {
            loggedIn = userDao.findByUsername(username);
            return true;
        }
        return false;
    }
    
    
    public User getLoggedIn() {
        return loggedIn;
    }

    /**
     * Metodin avulla käyttäjä kirjautuu ulos.
     */
    public void logout() {
        loggedIn = null;
    }

    /**
     * Metodin avulla lisätään uusi sanapari harjoittelua varten.
     *
     * @param fin Käyttäjän antama syöte (suomenkielinen sana)
     * @param foreign Käyttäjän antama syöte (vieraskielinen sana)
     *
     * @see kieltenharjoitteluohjelma.dao.LanguageDao#addWord(java.lang.Integer,
     * java.lang.String, java.lang.String)
     * @see Language#addWord(java.lang.String, java.lang.String)
     *
     * @return false, jos sanan lisääminen ei onnistunut ja true, jos sanan
     * lisääminen onnistui.
     */
    public Boolean addWord(String fin, String foreign) {
        try {
            languageDao.addWord(languageInt, fin, foreign);
            language.addWord(fin, foreign);
        } catch (Exception ex) {
            return false;
        }
        return true;
    }

    /**
     * Metodi päivittää kysyttävän sanan, jos sanoja harjoitellaan vieraasta
     * kielestä suomeen.
     */
    public void practiseForFinFirst() {
        wordToTranslate = language.randomFor();
        direction = 2;
    }

    /**
     * Metodi päivittää kysyttävän sanan, jos sanoja harjoitellaan suomesta
     * vieraaseen kieleen.
     */
    public void practiseFinForFirst() {
        wordToTranslate = language.randomFin();
        direction = 1;
    }

    /**
     * Metodin avulla tarkastetaan käyttäjän vastaus kysyttävään sanaan.
     *
     * @param answer Käyttäjän antama syöte (käyttäjän vastaus kysyttävään
     * sanaan)
     *
     * @see Language#translationFinFor(java.lang.String)
     * @see Language#translationForFin(java.lang.String)
     *
     * @return false, jos vastaus on väärin ja true, jos vastaus on oikein.
     */
    public Boolean practiseSecond(String answer) {
        if (direction == 1) {
            String correctAnswer = language.translationFinFor(wordToTranslate);
            if (answer.equals(correctAnswer)) {
                return true;
            }
        }
        if (direction == 2) {
            String correctAnswer = language.translationForFin(wordToTranslate);
            if (answer.equals(correctAnswer)) {
                return true;
            }
        }

        return false;
    }

    /**
     * Metodi palauttaa oikean vastauksen.
     *
     * @see Language#translationForFin(java.lang.String)
     * @see Language#translationFinFor(java.lang.String)
     *
     * @return palauttaa oikean vastauksen
     */
    public String getCorrectAnswer() {
        if (direction == 1) {
            String correctAnswer = language.translationFinFor(wordToTranslate);
            return correctAnswer;
        }
        String correctAnswer = language.translationForFin(wordToTranslate);
        return correctAnswer;
    }

    /**
     * Metodi asettaa harjoiteltavan kielen. 1 = englanti ja 2 = ruotsi.
     *
     * @param lang Kieltä kuvaava parametri.
     */
    public void setLanguage(int lang) {
        this.languageInt = lang;
    }

    public String getWordToTranslate() {
        return wordToTranslate;
    }

    /**
     * Metodi kutsuu LanguageDao-luokkaa ja päivittää tietokannasta saadut sanat
     * Language-luokalle.
     *
     */
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
