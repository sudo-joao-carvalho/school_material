package pt.isec.pa.aula8.ex19.ui;

import pt.isec.pa.aula8.ex19.model.books.Book;
import pt.isec.pa.aula8.ex19.model.books.OldBook;
import pt.isec.pa.aula8.ex19.model.books.RecentBook;
import pt.isec.pa.aula8.ex19.model.library.LibraryList;
import pt.isec.pa.utils.PAInput;

import java.util.ArrayList;

public class LibraryUI {
    LibraryList lib;
    public LibraryUI(LibraryList lib) {
        this.lib = lib;
    }

    void addBook() {
        int type = PAInput.chooseOption("Book type","Old Book","Recent books","Cancel operation");
        if (type == 3 || type < 1)
            return;

        String title = PAInput.readString("Book title: ",false);

        String author;
        ArrayList<String> authors = new ArrayList<>();

        do {
            author = PAInput.readString("Name of one author [\'exit\' to finish]: ",false);
            if (author!=null && !author.equalsIgnoreCase("exit"))
                authors.add(author);
        } while (!author.equalsIgnoreCase("exit"));

        if (authors.isEmpty())
            authors.add("Unknown author");

        int id = switch (type) {
            case 1 -> {
                int nCopies = PAInput.readInt("Number of copies: ");
                yield lib.addBook(new OldBook(title,authors,nCopies));
            }
            case 2 -> {
                String isbn = PAInput.readString("ISBN: ",false);
                double cost = PAInput.readNumber("Cost: ");
                yield lib.addBook(new RecentBook(title,authors,isbn,cost));
            }
            default -> -1;
        };

        if (id<0)
            System.out.println("Error adding this new books");
        else
            System.out.printf("The ID of this new books is: %d\n",id);

    }

    void findBook() {
        int bookId = PAInput.readInt("ID of the book to search: ");
        Book book = lib.findBook(bookId);
        if (book == null)
            System.out.println("Book not found");
        else
            System.out.println("Book found: "+book);
    }

    void removeBook() {
        int codigo = PAInput.readInt("ID of the book to remove: ");
        boolean deleted = lib.removeBook(codigo);
        if (!deleted)
            System.out.println("Book not found");
        else
            System.out.println("Book deleted");
    }

    public void start() {
        while (true) {
            switch (PAInput.chooseOption("Library Manager - "+lib.getNome(),
                    "Add new book","Search book","Remove book","Show books",
                    "Quit")) {
                case 1:
                    addBook();
                    break;
                case 2:
                    findBook();
                    break;
                case 3:
                    removeBook();
                    break;
                case 4:
                    System.out.println(lib.toString());
                    break;
                case 5:
                    return;
            }
        }
    }

}