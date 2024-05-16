package model.library;

import model.books.Book;

import java.util.*;
public class LibraryMap {

    private String name;

    private Map<Integer, Book> bookMap;

    //CONSTRUCTOR
    public LibraryMap(String name){
        this.name = name;
        this.bookMap = new HashMap<>();
    }

    //SETTERS & GETTERS
    public String getName(){return this.name;}
    public void setName(String name){this.name = name;}
    public int addBook(Book b){

        if(bookMap == null){
            return -1;
        }

        if(bookMap.containsKey(b.getCodigo())){
            return -1;
        }

        bookMap.put(b.getCodigo(), b);
        return b.getCodigo();
    }

    //public int addBook(String titulo, List<String> autores){return addBook(new Book(titulo, autores));}

    public Book findBookGetMethod(int codigo){

        if (bookMap == null)
            return null;

        return bookMap.get(codigo);
    }
}
