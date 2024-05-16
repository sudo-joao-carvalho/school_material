package pt.isec.pa.aula8.ex19.model.library;

import pt.isec.pa.aula8.ex19.model.books.Book;

import java.util.List;

public interface ILibrary {

    public String getNome();
    public void setNome(String nome);
    public int addBook(Book b);
    public int addBook(String titulo, List<String> autores);
    public Book findBook(int id);
    public boolean removeBook(int id);

}
