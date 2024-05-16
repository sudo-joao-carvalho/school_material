package models;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class LibrarySet {

    private String nome;
    private Set<Book> livrosSet;
    private int qtLivros;
    public LibrarySet(String nome){
        this.nome       = nome;
        livrosSet          = new HashSet<>();
        this.qtLivros   = 0;
    }

    public String getName() {return nome;}
    public int addBook(Book b){ //Set add
        livrosSet.add(b);
        return b.getCodigo();
    }
    public int addBook(String titulo, ArrayList<String> autores) {return addBook(new Book(titulo, autores));}

    public Book findBook(int codigo){

        //V1 --> iterativa
        /*
        if(livrosSet == null || livrosSet.isEmpty())
            return null;

        for(Book b: livrosSet){
            if(codigo == b.getCodigo()){
                return b;
            }
        }

        return null;*/

        //V2 --> com iterator
        if(livrosSet == null || livrosSet.isEmpty())
            return null;

        Iterator<Book> it = livrosSet.iterator();

        while(it.hasNext()){
            Book book = it.next();

            if(book.getCodigo() == codigo){
                return book;
            }
        }

        return null;

    }

    public boolean removeBook(int codigo){

        //V1 --> iterativa
        /*
        if(livrosSet == null || livrosSet.isEmpty()){
            return false;
        }

        for(Book b: livrosSet){
            if(codigo == b.getCodigo()){
                qtLivros--;
                return livrosSet.remove(b);
            }
        }

        return false;*/

        //V2 --> indexof()
        if(livrosSet == null || livrosSet.isEmpty()){
            return false;
        }

        livrosSet.remove(Book.getDummyBook(codigo));
        --qtLivros;
        return true;
    }


    @Override
    public String toString() {
        /*return "Library{" +
                "nome='" + nome + '\'' +
                ", lista=" + lista +
                '}';*/

        StringBuilder sb = new StringBuilder("Library " + nome + "\n");

        sb.append("Books:\n");

        if (livrosSet == null || livrosSet.size() == 0)
            sb.append("The library is empty");
        else
            for (Book book : livrosSet)
                //sb.append(String.format("\t- %s\n", book.toString()));
                sb.append("\t- " + book + "\n");

        return sb.toString();
    }
}
