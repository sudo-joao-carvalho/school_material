package models;

import java.util.List;
import java.util.ArrayList;

public class LibraryList {

    private String nome;
    private List<Book> bookList;

    // CONSTRUTOR
    public LibraryList(String nome) {
        this.nome = nome;
        this.bookList = new ArrayList<>();
    }

    // GETTERS & SETTERS
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    public int addBook(Book b) {
        bookList.add(b);
        return b.getCodigo();
    }
    public int addBook(String titulo, List<String> autores) {
        return addBook(new Book(titulo, autores));
    }

    public Book findBookIterativo(int id) {

        if (bookList == null || bookList.isEmpty())
            return null;

        for (Book livro : bookList) {
            if (livro.getCodigo() == id) {
                return livro;
            }
        }

        return null;
    }

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
            if (livro.getCodigo() == id) {
                bookList.remove(livro);
                return true;
            }
        }

        return false;
    }

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

        StringBuilder sb = new StringBuilder("Library " + nome + "\n");

        sb.append("Books:\n");

        if (bookList == null || bookList.size() == 0)
            sb.append("The library is empty");
        else
            for (Book book : bookList)
                //sb.append(String.format("\t- %s\n", book.toString()));
                sb.append("\t- " + book + "\n");

        return sb.toString();
    }
}
