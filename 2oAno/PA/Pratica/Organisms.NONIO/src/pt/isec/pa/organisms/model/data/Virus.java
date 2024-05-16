package pt.isec.pa.organisms.model.data;

import pt.isec.pa.organisms.model.ModelLog;

import java.util.List;

public class Virus extends Organism{
    public static final char SYMBOL = 'v';
    private static final double PROB = 0.75;
    private static final int MIN_VIRULENCE = 5;
    private static final int VIRUS_DEAD = 10;
    private int virulence;

    public Virus(Environment environment) {
        super(environment);
        virulence = 0;
    }

    @Override
    public char getSymbol() {
        return SYMBOL;
    }

    @Override
    public void evolve() {
        virulence++;
        if (virulence < MIN_VIRULENCE || Math.random()<PROB)
            return;
        Environment.Position myPos = environment.getPositionOf(this);
        if (myPos == null)
            return;
        if (virulence>VIRUS_DEAD) {
            ModelLog.getInstance().add("A virus died");
            environment.addOrganism(null,myPos.y(), myPos.x());
            return;
        }
        List<Environment.Position> lst = environment.getOrganismNeighbors(myPos.y(), myPos.x(), Evolver.class);
        if (lst.isEmpty())
            return;
        int ipos = (int) (Math.random() * lst.size());
        Environment.Position pos = lst.get(ipos);
        environment.addOrganism(new Virus(environment),pos.y(),pos.x());
        ModelLog.getInstance().add(String.format("New Virus at (%d,%d)",pos.y(),pos.x()));
    }
}
