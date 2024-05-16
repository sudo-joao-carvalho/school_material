package pt.isec.a2019135835.fichaEx.fourinarow.model.data;

import java.io.Serializable;

public enum Piece implements IMazeElement {
    YELLOW('Y'), RED('R');

    char letter;

    Piece(char letter) {
        this.letter = letter;
    }

    public Piece other() {
        return switch (this) {
            case YELLOW -> RED;
            case RED -> YELLOW;
            //default -> null;
        };
    }

    @Override
    public String toString() {
        return String.valueOf(letter);
    }

    @Override
    public char getSymbol() {
        return letter;
    }
}
