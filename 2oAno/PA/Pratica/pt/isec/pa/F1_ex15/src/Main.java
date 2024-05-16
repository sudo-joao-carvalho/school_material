import model.DictionaryList;
import model.DictionaryMap;

public class Main {
    public static void main(String[] args) {
        DictionaryMap d = new DictionaryMap();

        d.add("english", "BOOK", "book");
        d.add("francais", "BOOK", "livre");
        d.add("portugues", "BOOK", "livro");
        d.add("english", "YEAR", "year");
        d.add("francais", "YEAR", "an");
        d.add("portugues", "YEAR", "ano");

        d.setLanguage("english");
        System.out.println(d.get("YEAR"));
        d.setLanguage("portugues");
        System.out.println(d.get("YEAR"));
        d.setLanguage("francais");
        System.out.println(d.get("BOOK"));

    }
}