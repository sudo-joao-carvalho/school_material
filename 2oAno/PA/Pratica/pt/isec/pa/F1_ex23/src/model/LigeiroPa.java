package model;

import model.Vehicle;
public class LigeiroPa extends Vehicle implements IPassengers{

    private final int numPassengers;
    public LigeiroPa(String matricula, int ano, int numPassengers) {
        super(matricula, ano);
        this.numPassengers = numPassengers;
    }

    @Override
    public int getNumPassengers() {return this.numPassengers;}

    @Override
    public String toString() {
        return  "LigeiroPa{" + super.toString() +
                "numPassengers=" + numPassengers +
                '}';
    }
}
