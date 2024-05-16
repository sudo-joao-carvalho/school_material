package pt.isec.pa.aula8.ex19.model.library;

import pt.isec.pa.aula8.ex19.model.books.Book;

import java.util.*;

public class LibrarySet extends Library {

    //private String nome;
    private Set<Book> bookSet;

    // CONSTRUTOR
    public LibrarySet(String nome) {
        super(nome);
        this.bookSet = new HashSet<>();
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
        bookSet.add(b);
        return b.getID();
    }
    @Override
    public int addBook(String titulo, List<String> autores) {
        return addBook(new Book(titulo, autores));
    }

    public Book findBookIterativo(int id) {

        if (bookSet == null || bookSet.isEmpty())
            return null;

        for (Book livro : bookSet) {
            if (livro.getID() == id) {
                return livro;
            }
        }

        return null;
    }

    @Override
    public Book findBook(int id) {

        if (bookSet == null || bookSet.size() == 0)
            return null;

        Iterator<Book> it = bookSet.iterator();

        while (it.hasNext()) {
            Book book = it.next();

            if (book.getID() == id)
                return book;
        }

        return null;
    }

    public boolean removeBookIterativo(int id) {

        if (bookSet == null || bookSet.isEmpty())
            return false;

        for (Book livro : bookSet) {
            if (livro.getID() == id) {
                bookSet.remove(livro);
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean removeBook(int id) {

        if (bookSet == null)
            return false;

        return bookSet.remove(Book.getDummyBook(id));
    }

    @Override
    public String toString() {
        /*return "Library{" +
                "nome='" + nome + '\'' +
                ", lista=" + lista +
                '}';*/

        StringBuilder sb = new StringBuilder("Library " + super.getNome() + "\n");

        sb.append("Books:\n");

        if (bookSet == null || bookSet.size() == 0)
            sb.append("The library is empty");
        else
            for (Book book : bookSet)
                //sb.append(String.format("\t- %s\n", book.toString()));
                sb.append("\t- " + book + "\n");

        return sb.toString();
    }
}
