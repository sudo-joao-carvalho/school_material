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

            case 0 -> changeState(new GroundFloorState(context, elevator));
            case 1 -> changeState(new FirstFloorState(context, elevator));
            case 2 -> changeState(new SecondFloorState(context, elevator));
        }

        return true;

    }

    @Override
    public EElevatorState getState(){
        return EElevatorState.UNDER_MAINTENANCE;
    }
}
