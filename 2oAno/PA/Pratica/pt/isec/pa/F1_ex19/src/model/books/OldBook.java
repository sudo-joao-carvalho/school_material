package model.books;

import model.books.Book;

import java.util.List;

public class OldBook extends Book {
    private int nr_copies;
    public OldBook(String titulo, List<String> autores, int nr_copies) {
        super(titulo, autores); //chama o construtor com argumentos da classe base
        this.nr_copies = nr_copies;
    }

    //SETTERS & GETTERS
    public void setNum_copias(int nr_copies){this.nr_copies = nr_copies;}
    public int getNum_copias(){return this.nr_copies;}

    @Override
    public String toString(){
        return "*Old Book* " + super.toString() +
                ", #Copies: " + nr_copies;
    }
}
