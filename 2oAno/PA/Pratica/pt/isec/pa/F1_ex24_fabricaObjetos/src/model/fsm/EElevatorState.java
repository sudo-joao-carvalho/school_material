//o codigo que esta na fsm é para ter a menor logica possivel, é apenas para gerir a transiçao dos estados

package model.fsm;

import model.data.Elevator;
import model.fsm.states.FirstFloorState;
import model.fsm.states.GroundFloorState;
import model.fsm.states.SecondFloorState;
import model.fsm.states.UnderMaintenanceState;

public enum EElevatorState {

    GROUND_FLOOR, FIRST_FLOOR, SECOND_FLOOR
    //b
    , UNDER_MAINTENANCE;


    //factory - Classic implementation of factory pattern
    /*static IElevatorState createState(EElevatorState type, ElevatorContext context, Elevator elevator){
        return switch(type){
            case GROUND_FLOOR -> new GroundFloorState(type, context, elevator);
            case FIRST_FLOOR -> new FirstFloorState(type, context, elevator);
            case SECOND_FLOOR -> new SecondFloorState(type, context, elevator);
            case UNDER_MAINTENANCE -> new UnderMaintenanceState(type, context, elevator);
        };
    }*/

    IElevatorState createState(ElevatorContext context, Elevator elevator){
        return switch (this){
            case GROUND_FLOOR -> new GroundFloorState(context, elevator);
            case FIRST_FLOOR -> new FirstFloorState(context, elevator);
            case SECOND_FLOOR -> new SecondFloorState(context, elevator);
            case UNDER_MAINTENANCE -> new UnderMaintenanceState(context, elevator);
        };
    }


    /*
    GROUND_FLOOR,FIRST_FLOOR,SECOND_FLOOR;
 @Override
 public String toString(){
    return switch(this){
        case GROUND_FLOOR ->{yield "ground  floor";}
        case FIRST_FLOOR ->{yield "first  floor";}
        case SECOND_FLOOR ->{yield "second  floor";}

 }
  */

}

// a coisa comum entre todos os estados para sabermos se temos que fazer up ou down, tem que existir um currentState,
//mas nao podemos dizer que este estado é ground floor, flirst floor ou second floor pq pode ser qualquer um deles, entao
//tem que existir uma classe base, tipo uma classe abstrata onde os metodos n estao implementados e depois tem que haver polimorfismo
//neste caso existe a interface IState que tem o conjunto de metodos que vao ser implmentados
