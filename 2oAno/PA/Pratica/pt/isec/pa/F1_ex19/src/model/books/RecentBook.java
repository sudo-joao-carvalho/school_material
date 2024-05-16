package model.books;

import model.books.Book;

import java.util.List;

public class RecentBook extends Book {
    private String isbn;
    private double cost;
    public RecentBook(String titulo, List<String> autores, String isbn, double cost) {
        super(titulo, autores);
        this.isbn = isbn;
        this.cost = cost;
    }

    //SETTERS & GETTERS
    public void setISBN(String ISBN){this.isbn = ISBN;}
    public void setPreco(double cost){this.cost = cost;}
    public String getISBN(){return this.isbn;}
    public double getPreco(){return this.cost;}

    @Override
    public String toString(){
        return String.format("*Recent Book* %s, isbn: %s, cost: %.2f", super.toString(), isbn, cost);
    }
}
