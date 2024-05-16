package pt.isec.pa.organisms.model.data;

public abstract class Organism implements IMazeElement {
    protected Environment environment;

    protected Organism(Environment environment) {
        this.environment = environment;
    } // o facto de ter aqui o construtor eu depois n tenho que repetir nas classes derivadas

    abstract public void evolve();
}
