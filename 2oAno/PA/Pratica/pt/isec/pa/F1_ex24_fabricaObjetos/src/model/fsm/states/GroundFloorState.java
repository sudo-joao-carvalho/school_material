package model.fsm.states;

import model.data.Elevator;
import model.fsm.EElevatorState;
import model.fsm.ElevatorContext;
import model.fsm.ElevatorStateAdapter;
import model.fsm.states.FirstFloorState;

public class GroundFloorState extends ElevatorStateAdapter {

    public GroundFloorState(ElevatorContext context, Elevator elevator){
        super(context, elevator);
        elevator.setCurrentFloor(0);
    }

    @Override
    public boolean up(){ // aqui tem que se por a probabilidade do elevador avariar

        double d = Math.random();

        if(d > 0.9){
            changeState(EElevatorState.UNDER_MAINTENANCE);
            return true;
        }

        changeState(EElevatorState.FIRST_FLOOR);
        return true;
    }

    @Override
    public EElevatorState getState(){
        return EElevatorState.GROUND_FLOOR;
    }
}
