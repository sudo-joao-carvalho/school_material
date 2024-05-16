package model;
public class Carga extends Vehicle implements IMaxLoad{

    private final int maxWeight;

    public Carga(String matricula, int ano, int maxWeight){
        super(matricula, ano);
        this.maxWeight = maxWeight;
    }

    @Override
    public int getMaxWeight(){return this.maxWeight;}

    @Override
    public String toString() {
        return "Carga{" + super.toString() +
                "maxWeight=" + maxWeight +
                '}';
    }
}
