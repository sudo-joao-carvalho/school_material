package model.fsm.states;

import model.data.Elevator;
import model.fsm.EElevatorState;
import model.fsm.ElevatorContext;
import model.fsm.ElevatorStateAdapter;
import model.fsm.states.FirstFloorState;

public class SecondFloorState extends ElevatorStateAdapter {

    public SecondFloorState(ElevatorContext context, Elevator elevator){
        super(context, elevator);
        elevator.setCurrentFloor(2);
    }

    @Override
    public boolean down(){

        double d = Math.random();

        if(d > 0.7){
            changeState(new UnderMaintenanceState(context, elevator));
            return true;
        }

        changeState(new FirstFloorState(context, elevator));
        return true;
    }

    @Override
    public EElevatorState getState(){
        return EElevatorState.SECOND_FLOOR;
    }
}
