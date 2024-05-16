import model.library.LibraryList;
import ui.LibraryUI;
import model.library.LibrarySet;
public class Main {
    public static void main(String[] args) {
        LibraryList lib = new LibraryList("DEIS-ISEC");
        LibraryUI libUi = new LibraryUI(lib);
        libUi.start();
    }
}