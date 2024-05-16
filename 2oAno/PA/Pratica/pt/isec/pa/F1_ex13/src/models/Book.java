package models;

import java.util.ArrayList;
import java.util.Objects;

public class Book {

    private static int ID = 0;
    private int codigo;
    private String titulo;
    private ArrayList<String> autores;

    private Book(int codigo){   //funciona como objeto ficticio que me da jeito para eu pesquisar livros pelo codigo
        this.codigo = codigo;
        this.titulo = null;
        this.autores = null;
    }
    public Book(String titulo, ArrayList<String> autores){
        this.codigo     = ++ID;
        this.titulo     = titulo;
        this.autores    = autores;
    }

    public int getCodigo() {return codigo;}
    public ArrayList<String> getAutores() {return autores;}

    public static Book getDummyBook(int codigo){
        return new Book(codigo);
    }

    @Override
    public String toString(){

        StringBuilder sb = new StringBuilder();

        sb.append(String.format("Livro %d:\r\n\n", ID));
        sb.append(String.format("Titutlo: %s\r\n", titulo));
        sb.append(String.format("Autores: %s\r\n", autores.toString()));

        return sb.toString();
    }

    @Override
    public boolean equals(Object o){
        if(this == o) return true;
        if(!(o instanceof Book)) return false;  //alternativa o == null || getClass() != o.getClass()
        Book b = (Book) o;
        /*
            (if(!(o instanceof Book book)) return false;   //se tiver desta maneira nao preciso de dar cast e ao escrever ali book da cast automaticamente
        */
        return codigo == b.codigo;
    }

    @Override
    public int hashCode(){return Objects.hash(codigo);}
}
