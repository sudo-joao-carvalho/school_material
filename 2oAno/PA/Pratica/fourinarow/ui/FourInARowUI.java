package pt.isec.a2019135835.fichaEx.fourinarow.ui;

import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import pt.isec.a2019135835.fichaEx.fourinarow.model.FourInARowManager;
import pt.isec.a2019135835.fichaEx.fourinarow.model.data.Piece;


import java.io.IOException;

public class FourInARowUI {
    private static final int CELL_HEIGHT = 2+1;
    private static final int CELL_WIDTH  = 3+1;
    FourInARowManager game;

    public FourInARowUI(FourInARowManager game) {
        this.game = game;
    }

    public void start() {
        try (   Terminal terminal = new DefaultTerminalFactory().createTerminal();
                Screen screen = new TerminalScreen(terminal) ) {
            Piece winner = null;
            while (!game.isFull() && (winner = game.checkWinner()) == null) {
                int[] dim = show(screen);
                terminal.setCursorVisible(false);
                if (25-dim[0] > 5) {
                    terminal.setCursorPosition(0,21);
                    terminal.putString("Current user: "+game.getCurrentPlayer());
                    terminal.setCursorPosition(0,23);
                    terminal.putString("Press a column number or 'q' to quit or 's' to save or 'r' to redo or 'u' to undo");
                    terminal.flush();
                } // else the messages can be shown on the right side of the board
                KeyStroke key = screen.readInput();
                if (key.getKeyType() == KeyType.Character) {
                    char c = key.getCharacter();
                    if (c == 'q')
                        break;
                    else if(c=='s'){
                        game.save();
                        break;
                    }else if(c =='r'){
                        game.redo();
                    }else if(c=='u'){
                        game.undo();
                    }
                    else {
                        if (c >= '1' && c <= '1' + game.getWidth()) {
                            int move = c - '0';
                            game.play(move);
                        }
                    }
                }
            }

            show(screen);
            terminal.setCursorPosition(0,22);
            if (winner != null)
                terminal.putString("The winner was: " + winner);
            else
                terminal.putString("It was a draw");
            terminal.setCursorVisible(false);
            terminal.flush();
            Thread.sleep(5000);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {

        }
    }

    private int[] show(Screen screen) throws IOException {
        screen.startScreen();
        screen.clear();
        char [][] gameOutput = game.getBoard();
        TextGraphics tGraphics = screen.newTextGraphics();
        TextCharacter border = TextCharacter.fromCharacter('·',TextColor.ANSI.WHITE,TextColor.ANSI.BLACK)[0];
        for (int x = 0; x < game.getWidth(); x++) {
            tGraphics.drawLine(x*CELL_WIDTH,0, x*CELL_WIDTH, game.getHeight()*CELL_HEIGHT,'·');
        }
        tGraphics.drawLine(game.getWidth()*CELL_WIDTH,0, game.getWidth()*CELL_WIDTH, game.getHeight()*CELL_HEIGHT,'·');
        for(int y=0;y<game.getHeight();y++) {
            tGraphics.drawLine(0,y*CELL_HEIGHT, game.getWidth()*CELL_WIDTH, y*CELL_HEIGHT,'·');
            for (int x = 0; x < game.getWidth(); x++) {
                TextColor tc = switch (gameOutput[y][x]) {
                    case 'R' -> TextColor.ANSI.WHITE;
                    case 'Y' -> TextColor.ANSI.WHITE;
                    default -> TextColor.ANSI.WHITE;
                };
                TextColor bc = switch (gameOutput[y][x]) {
                    case 'R' -> TextColor.ANSI.RED_BRIGHT;
                    case 'Y' -> TextColor.ANSI.YELLOW_BRIGHT;
                    default -> TextColor.ANSI.BLACK;
                };
                TextCharacter inside = TextCharacter.fromCharacter(' ', tc, bc)[0];
                for (int iy = 0; iy < 2; iy++) {
                    for (int ix = 0; ix < 3; ix++) {
                        screen.setCharacter(x*CELL_WIDTH+ix+1,y*CELL_HEIGHT+iy+1,inside);
                    }
                }
            }
        }
        tGraphics.drawLine(0,game.getHeight()*CELL_HEIGHT, game.getWidth()*CELL_WIDTH, game.getHeight()*CELL_HEIGHT,'·');
        for(int x = 0; x < game.getWidth(); x++) {
            TextCharacter number = TextCharacter.fromCharacter(Character.forDigit(x+1,10), TextColor.ANSI.WHITE_BRIGHT, TextColor.ANSI.BLACK)[0];
            screen.setCharacter(x*CELL_WIDTH+2,(game.getHeight())*CELL_HEIGHT+1,number);
        }
        screen.refresh();
        return new int[] {game.getHeight()*CELL_HEIGHT+1,game.getWidth()*CELL_WIDTH};
    }
}
