package pt.isec.a2019135835.fichaEx.fourinarow.model;



import pt.isec.a2019135835.fichaEx.fourinarow.model.data.FourInARowData;
import pt.isec.a2019135835.fichaEx.fourinarow.model.data.Piece;
import pt.isec.a2019135835.fichaEx.fourinarow.model.memento.CareTaker;

import java.io.*;

public class FourInARowManager {
    private static final String FILE ="files/game.dat";
    FourInARowData data;
    CareTaker careTaker;
    public FourInARowManager() {
        careTaker = new CareTaker(data);
        load();
    }
    private void load(){
        File file = new File(FILE);
        try (FileInputStream finp = new FileInputStream(file);
             ObjectInputStream oinp = new ObjectInputStream(finp);)
        {
            ModelLog.getInstance().add("Existe um jogo guardado!");
            data = (FourInARowData) oinp.readObject();
            //file.delete();
        } catch (Exception e) {
            ModelLog.getInstance().add("Nao existe um jogo guardado!");
            data = new FourInARowData();
        }
    }

    public void save(){
        File file = new File(FILE);
        try(FileOutputStream fout = new FileOutputStream(file);
            ObjectOutputStream oout = new ObjectOutputStream(fout)){
            oout.writeObject(data);
            ModelLog.getInstance().add("Jogo guardado");
        } catch (Exception e) {
            ModelLog.getInstance().add("Erro a guardar o fichero");
            e.printStackTrace();
        }
    }

    public FourInARowManager(FourInARowData data) {
        this.data = data;
    }

    public void init() {
        data.init();
    }

    public Piece getCurrentPlayer() {
        return data.getCurrentPlayer();
    }

    public boolean play(int column) {
        careTaker.save();
        return data.play(column);
    }

    @Override
    public String toString() {
        return data.toString();
    }
    public boolean isFull() {
        return data.isFull();
    }

    public Piece checkWinner() {
        return data.checkWinner();
    }

    public char [][] getBoard() {
        return data.getBoard();
    }

    public int getHeight() {
        return data.getHeight();
    }

    public int getWidth() {
        return data.getWidth();
    }
    public void undo(){
        careTaker.undo();
    }
    public void redo(){
        careTaker.redo();
    }
}
