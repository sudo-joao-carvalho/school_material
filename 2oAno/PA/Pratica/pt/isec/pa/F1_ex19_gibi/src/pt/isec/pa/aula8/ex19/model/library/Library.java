package pt.isec.pa.aula8.ex19.model.library;

import pt.isec.pa.aula8.ex19.model.books.Book;

import java.util.List;

public abstract class Library implements ILibrary {
    //usa se o implements pq estamos a implementar as funcoes da class ILibrary, como o nome da library é comum ou basicamente
    //como so vai existir uma library criamos uma classe intermediaria que tem estes atributos comuns ou neste caso o nome da livraria
    //caso todas as funcoes que existem na classe ILibrary nao estivessem implementadas nesta class teriamos que usar o extends noutra class para a implementar

    //Como a class Book é protected nao conseguimos ir busca la fora da package

    private String nome;

    public Library(String nome) {
        this.nome = nome;
    }

    @Override
    public String getNome() {
        return nome;
    }

    @Override
    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public int addBook(String titulo, List<String> autores) {
        return addBook(new Book(titulo, autores));
    }

    @Override
    public String toString() {
        return "Library: " + nome + "\n";
    }
}
