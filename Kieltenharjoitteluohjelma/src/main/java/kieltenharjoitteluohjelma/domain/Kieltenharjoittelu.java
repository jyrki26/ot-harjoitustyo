
package kieltenharjoitteluohjelma.domain;

import java.util.HashMap;
import java.util.TreeMap;


public class Kieltenharjoittelu {
    
    private HashMap<String, String> english;
    private HashMap<String, String> swedish;
    private TreeMap<Integer, HashMap> languages;

    public Kieltenharjoittelu() {
        this.english = new HashMap<>();
        this.swedish = new HashMap<>();
        this.languages = new TreeMap<>();
        this.languages.put(1, english);
        this.languages.put(2, swedish);
    }
    
    
    public TreeMap<Integer, HashMap> getLanguages(){
        return languages;
    }
    
    public void addWord(Integer language, String fin, String foreign){
        if(language == 1){
            english.put(fin, foreign);
        }
        if(language == 2){
            swedish.put(fin, foreign);
        }
    }
    
}
