package model.fsm;

import model.data.Elevator;
import model.fsm.states.GroundFloorState;

//esta class é onde estao os dados e onde se chama o up e o down

//no tp como vamos usar as teclas tambem é possivel termos um up down left right

//nos atraves do context nao podemos ter getDados nem getEstadoAtual, nao podemos ter estas funcoes com referencias para os dados nem para o estadoAtual(fortemente penalizado no trabalho)

//normalmente no contexto temos os mesmos metodos que na interface, mas serve apenas de ponte entre a interface e o os proprios estados
public class ElevatorContext {

    IElevatorState state;

    Elevator elevator;

    public ElevatorContext(){
        elevator = new Elevator(0 ,"OLAOLA");
        state = new GroundFloorState(this, elevator);
    }

    //este metodos fazem ponte para a implementacao dos metodos
    public EElevatorState getState(){
        return state.getState(); //pode ser fazer isto pq vai buscar a enum
    }

    void changeState(IElevatorState newState){
        this.state = newState;
    }

    public boolean up(){return state.up();}

    public boolean down(){return state.down();}

    //b)
    public boolean useSecurityKey(String key){return state.useSecurityKey(key);}

    //get data (ir buscar coisas ao modelo de dados)
    public int getCurrentFloor(){
        return elevator.getCurrentFloor();
    }
}
