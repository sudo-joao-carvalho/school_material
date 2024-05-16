package model;

import model.Vehicle;
public class PesadosPa extends Vehicle implements IPassengers, IMaxLoad{

    private final int numPassangers;
    private final int maxWeight;

    public PesadosPa(String matricula, int ano, int numPassangers, int maxWeight){
        super(matricula, ano);
        this.numPassangers = numPassangers;
        this.maxWeight = maxWeight;
    }

    @Override
    public int getMaxWeight() {return this.maxWeight;}

    @Override
    public int getNumPassengers() {return this.numPassangers;}

    @Override
    public String toString() {
        return "PesadosPa{" + super.toString() +
                "numPassangers=" + numPassangers +
                ", maxWeight=" + maxWeight +
                '}';
    }
}
