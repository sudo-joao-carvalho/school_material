package pt.isec.a2019135835.fichaEx.fourinarow;


import pt.isec.a2019135835.fichaEx.fourinarow.model.FourInARowManager;
import pt.isec.a2019135835.fichaEx.fourinarow.ui.FourInARowUI;

public class FourInARow {
    public static void main(String[] args) {
        FourInARowManager game = new FourInARowManager();
        FourInARowUI gameui= new FourInARowUI(game);
        gameui.start();
    }
}
