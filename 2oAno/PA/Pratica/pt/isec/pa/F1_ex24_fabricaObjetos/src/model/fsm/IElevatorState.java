package model.fsm;

//Esta interface tem os metodos que o state aplica, sao os metodos que se poe no diagrama
public interface IElevatorState {

    boolean up();
    boolean down();

    boolean useSecurityKey(String key); //b)
    EElevatorState getState();

}
