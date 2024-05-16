package pt.isec.pa.exerc11.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Report {
    private String title;
    private ArrayList<String> authors;
    int qtAuthors;
    private StringBuilder text;
    public Report(String title){
        this.title      = title;
        this.authors    = new ArrayList<>();
        this.text       = new StringBuilder();
        this.qtAuthors  = 0;
    }

    public String getTitle(){return title;}

    public void setTitle(String title){this.title = title;}

    public String getText(){return text.toString();}

    public boolean addAuthor(String newAuthor){
       if(authors.contains(newAuthor)){
           return false;
       }

       authors.add(newAuthor);
       return true;
    }

    public boolean removeAuthor(String author){
        return authors.remove(author); //o remove da return a um bolean
    }

    public void addText(String newText){
        text.append(newText);
    }

    public void capitalizeSentences(){
        boolean changeNext = true;

        for(int i = 0; i < text.length(); i++){
            char c = text.charAt(i);

            if(changeNext && Character.isLetter(c)){
                text.setCharAt(i, Character.toUpperCase(c));
                changeNext = false;
            }else if(".!?".indexOf(c) >= 0)
                changeNext = true;
        }

    }
    public int getNumberOfWords(){
        StringTokenizer st = new StringTokenizer(text.toString(), " \t\n\r,.!?");
        return st.countTokens();
    }

    public int getNumberOfOccurrences(String word){
        int counter = 0;
        StringTokenizer st = new StringTokenizer(text.toString(), " \t\n\r,.!?");
        while(st.hasMoreTokens()){
            if(word.equalsIgnoreCase(st.nextToken()))
                counter++;
        }

        return counter;
    }

    @Override
    public String toString() {
        /*
        return "Report{" +
                "title='" + title + '\'' +
                ", authors=" + Arrays.toString(authors) +
                ", qtAuthors=" + qtAuthors +
                ", text=" + text +
                '}';
         */

        //VERSAO STOR
        StringBuilder sb = new StringBuilder("Report\r\n");
        sb.append(String.format("Title: %s\r\n", title));
        sb.append("Authors: ");
        if(qtAuthors > 0){
            for(int i = 0; i < qtAuthors; i++){
                if(i > 0)
                    sb.append(", ");
                sb.append(authors.get(i));
            }
        }
        sb.append("\r\nText: \r\n");
        sb.append(text);
        return sb.toString();
    }
}
