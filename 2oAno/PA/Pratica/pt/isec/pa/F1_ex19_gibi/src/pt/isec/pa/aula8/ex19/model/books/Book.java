package pt.isec.pa.aula8.ex19.model.books;

import java.util.List;

public class Book implements Comparable<Book> {

    // variavel estatica, INDEPENDENTE das várias instâncias
    private static int countID = 0;

    // método estático para retornar o contador
    private static int getNewID() {
        return ++countID;
    }

    private int id;
    private String titulo;
    private List<String> autores;

    // CONSTRUTOR
    protected Book(String titulo, List<String> autores) {
        this.id = getNewID();
        this.titulo = titulo;
        this.autores = autores;
    }

    protected Book(String titulo, String... autores) {
        this.id = getNewID();
        this.titulo = titulo;
        this.autores = List.of(autores);
    }

    // GETTERS & SETTERS
    public int getID() {
        return id;
    }
    public String getTitulo() {
        return titulo;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    public List<String> getAutores() {
        return autores;
    }
    public void setAutores(List<String> autores) {
        this.autores = autores;
    }


    @Override
    public String toString() {
        /*return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", authors=" + authors +
                '}';*/

        if (titulo == null)
            return "DummyBook [" + id + ']';

        if (autores == null || autores.size() == 0)
            return "[" + id + "],'" + titulo + "'";

        String strAuthors = autores.toString();

        return "[" + id + "]'" + titulo + "', " + strAuthors.substring(1, strAuthors.length() -1);
    }


    @Override
    public boolean equals(Object o) {
        // se a referência para o objeto for o mesmo, então é o objeto é exatamente o mesmo
        if (this == o)
            return true;
        /*
        // se a referência para o objeto for null, ou a classe não é a mesma, então não sao iguais
        if (o == null || getClass() != o.getClass()) // !(o instanceof Book)
            return false;
        */

        // temos que usar o instanceof porque
        //    com o getClass, os RecentBook/OldBook vão ser sempre diferente de Book
        //    com o instanceof, os RecentBook/OldBook são derivações de Book
        if (o == null || !(o instanceof Book))
            return false;

        // depois destas validações, fazemos um cast do Object para o tipo que queremos
        Book book = (Book) o;

        //return id == book.id && Objects.equals(titulo, book.titulo) && Objects.equals(autores, book.autores);

        // como no enunciado diz que dois livros são o mesmo se tiverem o mesmo código (aquele que é estático)
        return id == book.id;
    }

    @Override
    public int hashCode() {
        //return Objects.hash(id, titulo, autores);

        // o hashCode deve ser o mesmo para objetos que são considereados iguais
        return id;
    }

    // como é dito no enunciado, tem que haver um método que basicamente cria um livro "vazio"
    public static Book getDummyBook(int id) {
        return new Book(id);
    }

    // este construtor é necessário para a criação do DummyBook
    private Book(int id) {
        this.id = id;
        this.titulo = null;
        this.autores = null;
    }

    @Override
    public int compareTo(Book o) {
        return id - o.id;
        //Outro exemplo: return titulo.compareTo(o.titulo);
    }
}
