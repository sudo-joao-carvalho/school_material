package pt.isec.pa.organisms.model.data;

import pt.isec.pa.organisms.model.ModelLog;

import java.util.Collections;
import java.util.List;

public class Evolver extends Organism {
    public static final char SYMBOL = 'E';
    private static final double PROB = 0.75;
    private static final int REPRODUCE_MIN = 2;
    private static final int REPRODUCE_LIM = 20;
    private int reproduceController;

    public Evolver(Environment environment) {
        super(environment);
        reproduceController = 0;
    }

    @Override
    public char getSymbol() {
        return SYMBOL;
    }

    @Override
    public void evolve() {
        reproduceController++;
        Environment.Position myPos = environment.getPositionOf(this);
        if (myPos == null)
            return;
        if (reproduceController>REPRODUCE_LIM) {
            ModelLog.getInstance().add("A Evolver died");
            environment.addOrganism(null,myPos.y(), myPos.x());
            return;
        }
        List<Environment.Position> lst_e1= environment.getAdjacentEmptyCells(myPos.y(), myPos.x());
        if (lst_e1.isEmpty())
            return;
        if (Math.random()<PROB) {
            int ipos = (int) (Math.random() * lst_e1.size());
            Environment.Position newpos = lst_e1.get(ipos);
            environment.addOrganism(null,myPos.y(), myPos.x());
            environment.addOrganism(this, newpos.y(), newpos.x());
            return;
        }
        if (reproduceController<REPRODUCE_MIN)
            return;

        List<Environment.Position> lst = environment.getOrganismNeighbors(myPos.y(), myPos.x(), Evolver.class);
        if (lst.isEmpty())
            return;
        Collections.shuffle(lst);

        for(Environment.Position pos : lst) {
            List<Environment.Position> lst_e2= environment.getAdjacentEmptyCells(pos.y(), pos.x());
            lst_e2.retainAll(lst_e1);
            if (lst_e2.size()>0) {
                int ipos = (int) (Math.random() * lst_e2.size());
                Environment.Position pos_son = lst_e2.get(ipos);
                environment.addOrganism(new Evolver(environment),pos_son.y(),pos_son.x());
                reproduceController = 0;
                ((Evolver)environment.getOrganism(pos.y(), pos.x())).reproduceController=0;
                ModelLog.getInstance().add(String.format("New Evolver at (%d,%d)",pos_son.y(),pos_son.x()));
                return;
            }
        }
    }
}
