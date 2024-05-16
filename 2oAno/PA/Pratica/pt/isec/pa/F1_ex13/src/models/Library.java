package models;

import java.util.ArrayList;

public class Library {

    private String nome;
    private ArrayList<Book> livros;
    private int qtLivros;
    public Library(String nome){
        this.nome       = nome;
        livros          = new ArrayList<>();
        this.qtLivros   = 0;
    }

    public String getName() {return nome;}
    public int addBook(String titulo, ArrayList<String> autores) {
        Book livro = new Book(titulo, autores);
        livros.add(livro);
        qtLivros++;

        return livro.getCodigo();
    }

    public Book findBook(int codigo){

        //V1 --> iterativa
        /*for(Book b: livros){
            if(codigo == b.getCodigo()){
                return b;
            }
        }

        return null;*/

        //V2 --> indexof();
        if(livros == null || livros.size() == 0)
            return null;

        Book dummyBook = Book.getDummyBook(codigo);
        int index = livros.indexOf(dummyBook);
        if(index < 0)
            return null;
        return livros.get(index);


    }

    public boolean removeBook(int codigo){

        //V1 --> iterativa
        /*for(Book b: livros){
            if(codigo == b.getCodigo()){
                qtLivros--;
                return livros.remove(b);
            }
        }

        return false;*/

        //V2 --> indexof()
        if(livros == null || livros.size() == 0){
            return false;
        }

        Book dummyBook = Book.getDummyBook(codigo);
        int index = livros.indexOf(dummyBook);
        if(index < 0){
            return false;
        }

        livros.remove(index);
        --qtLivros;
        return true;
    }


    @Override
    public String toString() {
        return "Library{" +
                "nome='" + nome + '\'' +
                ", livros=" + livros +
                ", qtLivros=" + qtLivros +
                '}';
    }
}
