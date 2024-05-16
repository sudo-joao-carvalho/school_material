package model.library;

import model.books.Book;
import model.books.PublicBook;
import java.util.List;

public abstract class Library implements ILibrary{

    private String nome;

    public Library(String nome) {
        this.nome = nome;
    }
    @Override
    public String getNome(){return this.nome;}
    @Override
    public void setNome(String nome){this.nome = nome;}
    @Override
    public int addBook(String titulo, List<String> autores) {
        return addBook(new PublicBook(titulo, autores));
    }

    @Override
    public String toString() {
        return "Library: " + nome + "\n";
    }
}
