public abstract class MelgaAdapter implements IStates {

    protected MelgaData melgaData;
    protected MelgaContext melgaContext;
    protected MelgaAdapter(MelgaContext melgaContext, MelgaData melgaData){
        this.melgaContext = melgaContext;
        this.melgaData = melgaData;
    }

    protected void changeState(EMelgaState newState){
        melgaContext.setState(newState.criaMelgaState(melgaContext, melgaData));
    }

    @Override
    public void mexe(){}

    @Override
    public void picar(){}

    @Override
    public void descolar(){}

    @Override
    public boolean tentaEsmagar(){return false;}
}
