package pt.isec.pa.aula8.ex19.model.library;

import pt.isec.pa.aula8.ex19.model.books.Book;
import pt.isec.pa.aula8.ex19.model.library.Library;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LibraryList extends Library {

    //private String nome;
    private List<Book> bookList;

    // CONSTRUTOR
    public LibraryList(String nome) {
        //this.nome = nome;
        super(nome);
        this.bookList = new ArrayList<>();
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
        bookList.add(b);
        return b.getID();
    }

    @Override
    public int addBook(String titulo, List<String> autores) {
        return addBook(new Book(titulo, autores));
    }

    public Book findBookIterativo(int id) {

        if (bookList == null || bookList.isEmpty())
            return null;

        for (Book livro : bookList) {
            if (livro.getID() == id) {
                return livro;
            }
        }

        return null;
    }

    @Override
    public Book findBook(int id) {

        if (bookList == null || bookList.isEmpty())
            return null;

        int pos = bookList.indexOf(Book.getDummyBook(id));

        if (pos != -1)
            return bookList.get(pos);

        return null;
    }

    public boolean removeBookIterativo(int id) {

        if (bookList == null || bookList.isEmpty())
            return false;

        for (Book livro : bookList) {
            if (livro.getID() == id) {
                bookList.remove(livro);
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean removeBook(int id) {

        if (bookList == null || bookList.isEmpty())
            return false;

        int pos = bookList.indexOf(Book.getDummyBook(id));

        if (pos != -1) {
            bookList.remove(pos);
            return true;
        }

        return false;
    }

    @Override
    public String toString() {
        /*return "Library{" +
                "nome='" + nome + '\'' +
                ", lista=" + lista +
                '}';*/

        StringBuilder sb = new StringBuilder("Library " + super.getNome() + "\n");

        sb.append("Books:\n");

        if (bookList == null || bookList.size() == 0)
            sb.append("The library is empty");
        else
            for (Book book : bookList)
                //sb.append(String.format("\t- %s\n", book.toString()));
                sb.append("\t- " + book + "\n");

        return sb.toString();
    }

    //@Override
    public String toStringOtherOrder() {
        /*return "Library{" +
                "nome='" + nome + '\'' +
                ", lista=" + lista +
                '}';*/

        StringBuilder sb = new StringBuilder("Library " + super.getNome() + "\n");

        sb.append("Books:\n");

        if (bookList == null || bookList.size() == 0)
            sb.append("The library is empty");
        else {
            List<Book> orderList = new ArrayList<>(bookList);
            Collections.sort(orderList);
            for (Book book : orderList)
                //sb.append(String.format("\t- %s\n", book.toString()));
                sb.append("\t- " + book + "\n");
        }
        return sb.toString();
    }
}
