import ui.LibraryUI;
import models.Library;
public class Main {
    public static void main(String[] args) {
        Library library = new Library("Libraria Lello");
        LibraryUI libraryUI = new LibraryUI(library);

        libraryUI.start();
    }
}