package model.fsm.states;

import model.data.Elevator;
import model.fsm.*;

public class FirstFloorState extends ElevatorStateAdapter {

    public FirstFloorState(ElevatorContext context, Elevator elevator){
        super(context, elevator);
        elevator.setCurrentFloor(1);
    }

    @Override
    public boolean up(){
        double d = Math.random();

        if(d > 0.8){
            changeState(EElevatorState.UNDER_MAINTENANCE);
            return true;
        }

        changeState(EElevatorState.SECOND_FLOOR);
        return true;
    }

    @Override
    public boolean down(){
        double d = Math.random();

        if(d > 0.8){
            changeState(EElevatorState.UNDER_MAINTENANCE);
            return true;
        }

        changeState(EElevatorState.GROUND_FLOOR);
        return true;
    }

    @Override
    public EElevatorState getState(){
        return EElevatorState.FIRST_FLOOR;
    }
}
