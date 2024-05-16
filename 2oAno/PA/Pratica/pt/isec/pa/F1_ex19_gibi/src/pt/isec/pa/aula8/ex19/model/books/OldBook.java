package pt.isec.pa.aula8.ex19.model.books;

import java.util.List;

public class OldBook extends Book {

    private int nCopias;

    // CONSTRUTOR
    public OldBook(String titulo, List<String> autores, int nCopias) {
        super(titulo, autores);
        this.nCopias = nCopias;
    }

    // GETTERS & SETTERS
    public int getNCopias() {
        return nCopias;
    }

    public void setNCopias(int nCopias) {
        this.nCopias = nCopias;
    }

    @Override
    public String toString() {
        return "*Old Book* " + super.toString() + ", Copias: " + nCopias;
    }

}
