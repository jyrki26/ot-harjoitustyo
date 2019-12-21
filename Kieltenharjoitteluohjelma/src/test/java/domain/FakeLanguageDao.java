package domain;

import java.sql.SQLException;
import java.util.HashMap;
import kieltenharjoitteluohjelma.dao.LanguageDao;

public class FakeLanguageDao implements LanguageDao {

    HashMap<String, String> wordList = new HashMap<>();

    public FakeLanguageDao() {
        this.wordList.put("pallo", "ball");
    }

    @Override
    public HashMap<String, String> words(Integer language) throws SQLException {
        return wordList;
    }

    @Override
    public boolean addWord(Integer language, String finnish, String foreign) throws SQLException {
        this.wordList.put(finnish, foreign);
        return true;
    }

}
