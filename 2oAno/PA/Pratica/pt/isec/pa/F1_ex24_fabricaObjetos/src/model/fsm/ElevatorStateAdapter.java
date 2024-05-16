package model.fsm;

import model.data.Elevator;

//como em todos os states existe uma coisa comum que é os metodos up e down atuam smp sobre o elevador entao podemos criar uma class abstrata
//onde n se implementam os metodos up and down e que depois todos os estados vao derivar desta class abstrata
public abstract class ElevatorStateAdapter implements IElevatorState {

    //estes atributos estao protected porque eu quero que apenas as classes que estao na mm package possam aceder a estes atributos
    protected Elevator elevator;

    protected ElevatorContext context;

    protected ElevatorStateAdapter(ElevatorContext context, Elevator elevator){
        this.context    = context;
        this.elevator   = elevator;
    }

    protected void changeState(EElevatorState newState){context.changeState(newState.createState(context, elevator));}

    //aqui a implementacao dos metodos é feita por omissao, basicamente n faz nada
    @Override
    public boolean up(){return false;}

    @Override
    public boolean down(){return false;}

    @Override
    public boolean useSecurityKey(String key){return false;}
}
