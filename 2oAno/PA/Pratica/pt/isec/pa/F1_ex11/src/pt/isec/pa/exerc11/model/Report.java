package pt.isec.pa.exerc11.model;

import java.util.ArrayList;
import java.util.Arrays;

public class Report {
    //private ArrayList<String> authors;

//Versao1
    private static final int INC_AUTHORS = 5;
    private String title;
    private String[] authors;
    int qtAuthors;
    private StringBuilder text;
    public Report(String title){
        this.title      = title;
        this.authors    = new String[INC_AUTHORS];
        this.text       = new StringBuilder();
        this.qtAuthors  = 0;
    }

    public String getTitle(){return title;}

    public void setTitle(String title){this.title = title;}

    public String getText(){return text.toString();}

    public boolean addAuthor(String newAuthor){
        //VERSAO COM ARRAYS
        if(qtAuthors >= authors.length){
            //return false; -->v1
            //quando ja esta cheio, aumentar o array -->v2
            /*String[] newAuthors = new String[authors.length + INC_AUTHORS];
            System.arraycopy(authors, 0, newAuthors, 0, authors.length);
            authors = newAuthors;*/
            //-->v3
            authors = Arrays.copyOf(authors, authors.length + INC_AUTHORS);
        }

        for(String a: authors){
            if(a.equalsIgnoreCase(newAuthor)){
                return false;
            }
        }

        authors[qtAuthors++] = newAuthor;
        return true;

    } //se o array esta cheio return false

    public boolean removeAuthor(String author){

        for(int i = 0; i < qtAuthors; i++){
            if(authors[i].equalsIgnoreCase(author)){
                for(int j = i + 1; j < qtAuthors; j++){
                    authors[j - 1] = authors[j];
                }
                qtAuthors--;
                authors[qtAuthors] = null;
                return true;
            }
        }
        return false;
    }

    public void addText(String newText){
        /*if(text == null) //nao e necessario
            text = new StringBuilder();
        else*/ text.append(newText);
    }

    public void capitalizeSentences(){

    }
    public int getNumberOfWords(){return 0;}

    public int getNumberOfOccurrences(String word){return 0;}

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
                sb.append(authors[i]);
            }
        }
        sb.append("\r\nText: \r\n");
        sb.append(text);
        return sb.toString();
    }
}
