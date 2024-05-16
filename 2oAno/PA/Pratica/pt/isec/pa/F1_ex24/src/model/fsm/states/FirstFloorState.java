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
            changeState(new UnderMaintenanceState(context, elevator));
            return true;
        }

        changeState(new SecondFloorState(context, elevator));
        return true;
    }

    @Override
    public boolean down(){
        double d = Math.random();

        if(d > 0.8){
            changeState(new UnderMaintenanceState(context, elevator));
            return true;
        }

        changeState(new GroundFloorState(context, elevator));
        return true;
    }

    @Override
    public EElevatorState getState(){
        return EElevatorState.FIRST_FLOOR;
    }
}
