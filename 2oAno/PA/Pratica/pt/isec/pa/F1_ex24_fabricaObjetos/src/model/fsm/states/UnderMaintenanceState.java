package model.fsm.states;

import model.data.Elevator;
import model.fsm.EElevatorState;
import model.fsm.ElevatorContext;
import model.fsm.ElevatorStateAdapter;

public class UnderMaintenanceState extends ElevatorStateAdapter {

    public UnderMaintenanceState(ElevatorContext context, Elevator elevator){
        super(context, elevator);
        elevator.enterMaintenance();
    }

    @Override
    public boolean useSecurityKey(String key){
        if(!elevator.leaveMaintenance(key))
            return false;

        switch(elevator.getCurrentFloor()){

            case 0 -> changeState(EElevatorState.GROUND_FLOOR);
            case 1 -> changeState(EElevatorState.FIRST_FLOOR);
            case 2 -> changeState(EElevatorState.SECOND_FLOOR);
        }

        return true;

    }

    @Override
    public EElevatorState getState(){
        return EElevatorState.UNDER_MAINTENANCE;
    }
}
