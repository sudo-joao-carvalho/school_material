package model.books;

import model.books.Book;

import java.util.List;

public class PublicBook extends Book {

    public PublicBook(String titulo, List<String> autores){
        super(titulo, autores);
    }
}
