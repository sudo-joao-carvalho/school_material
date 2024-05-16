package pt.isec.pa.aula8.ex19.model.books;

import java.util.List;

public class RecentBook extends Book {

    private String isbn;
    private double preco;

    // CONSTRUTOR
    public RecentBook(String titulo, List<String> autores, String isbn, double preco) {
        super(titulo, autores);
        this.isbn = isbn;
        this.preco = preco;
    }

    // GETTERS & SETTERS
    public String getISBN() {
        return isbn;
    }
    public void setISBN(String isbn) {
        this.isbn = isbn;
    }
    public double getPreco() {
        return preco;
    }
    public void setPreco(double preco) {
        this.preco = preco;
    }

    @Override
    public String toString() {
        return String.format("*Recent Book* %s, ISBN: %s, preço: %.2f ", super.toString(), isbn, preco);
    }

}
