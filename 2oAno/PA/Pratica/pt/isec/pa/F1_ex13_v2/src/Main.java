import ui.LibraryUI;
import models.LibrarySet;
public class Main {
    public static void main(String[] args) {
        LibrarySet library = new LibrarySet("Libraria Buceta");
        LibraryUI libraryUI = new LibraryUI(library);

        libraryUI.start();
    }
}