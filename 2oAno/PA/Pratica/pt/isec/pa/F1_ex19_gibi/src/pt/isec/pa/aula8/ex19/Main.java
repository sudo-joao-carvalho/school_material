package pt.isec.pa.aula8.ex19;

import pt.isec.pa.aula8.ex19.model.library.LibraryList;
import pt.isec.pa.aula8.ex19.ui.LibraryUI;

public class Main {
    public static void main(String[] args) {
        LibraryList lib = new LibraryList("DEIS-ISEC");
        LibraryUI libUi = new LibraryUI(lib);
        libUi.start();
    }
}