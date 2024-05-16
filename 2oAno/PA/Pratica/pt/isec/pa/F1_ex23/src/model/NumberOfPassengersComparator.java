package model;

import java.util.Comparator;

public class NumberOfPassengersComparator implements Comparator<IPassengers> {

    @Override
    public int compare(IPassengers o1, IPassengers o2) {
        return o2.getNumPassengers() - o1.getNumPassengers();
    }

}