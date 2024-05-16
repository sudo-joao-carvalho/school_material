package pt.isec.pa.aula8.ex19.model.library;

import pt.isec.pa.aula8.ex19.model.books.Book;

import java.util.*;

public class LibraryMap extends Library {

    //private String nome;
    private Map<Integer, Book> bookMap;

    // CONSTRUTOR
    public LibraryMap(String nome) {
        super(nome);
        this.bookMap = new HashMap<>();
    }

    // GETTERS & SETTERS
    /*
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    */
    @Override
    public int addBook(Book b) {

        if (bookMap == null)
            return -1;

        if (bookMap.containsKey(b.getID()))
            return -1;

        bookMap.put(b.getID(), b);
        return b.getID();
    }
    @Override
    public int addBook(String titulo, List<String> autores) {
        return addBook(new Book(titulo, autores));
    }

    public Book findBook1(int id) {

        if (bookMap == null)
            return null;

        return bookMap.get(id);
    }

    @Override
    public Book findBook(int id) {

        Collection<Book> values = bookMap.values();

        //Set<Integer> keys = bookMap.keySet();   // keySet e não KeyList porque as chaves são UNICAS

        for (Book book : values)
            if (id == book.getID())
                return book;

        return null;
    }

    @Override
    public boolean removeBook(int id) {

        if (bookMap == null)
            return false;

        return bookMap.remove(id) != null;
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Library " + super.getNome() + "\n");

        sb.append("Books:\n");

        if (bookMap == null || bookMap.isEmpty())
            sb.append("The library is empty");
        else
            /*
            Collection<Book> values = bookMap.values();
            for (Book book : values)*/
            for (Book book : bookMap.values())
                //sb.append(String.format("\t- %s\n", book.toString()));
                sb.append("\t- " + book + "\n");

        return sb.toString();
    }
}
