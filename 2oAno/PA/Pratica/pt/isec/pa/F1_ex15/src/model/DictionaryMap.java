package model;

import java.util.HashMap;
import java.util.Map;

public class DictionaryMap {
    private String language;
    private Map<String, Map<String, String>> dictionary;

    public DictionaryMap(){
        this.language       = "";
        this.dictionary     = new HashMap<>();
    }

    public void setLanguage(String language){this.language = language;}
    public String getLanguage(){return this.language;}
    public void add(String language, String word, String translation){

        if(dictionary == null)
            return ;

        /*if(!dictionary.containsKey(word)){
            if(!traduction.containsKey(language)){
                traduction.put(language, translation);
                dictionary.put(word, traduction);
                return "true1";
            }else{
                traduction.put(language, translation);
                return "true12";
            }
        }else{
            if(!traduction.containsKey(language)){
                traduction.put(language, translation);
                return "true2";
            }
        }*/

        if(!dictionary.containsKey(language)){
            dictionary.put(language, new HashMap<>());
        }

        dictionary.get(language).put(word, translation);


    }

    public String get(String word){


        if(dictionary.containsKey(language)){
            Map<String, String> mp = dictionary.get(language);

            if(mp.containsKey(word))
                return mp.get(word);
        }

        return "Nada";
    }

}
