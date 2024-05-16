public class MelgaContext {

    private MelgaData data;
    private IStates state;

    public MelgaContext(){
        this.data = new MelgaData();
        this.state = new VooState(this, data);
    }

    void setState(IStates state){this.state=state;}
    public EMelgaState getState() {return this.state.getState();}

    /* TODO -> executa funcoes que estao dentro dos estados */
    void mexe() {state.mexe();}
    void picar() {state.picar();}
    void descolar() {state.descolar();}
    boolean tentaEsmagar() {return state.tentaEsmagar();}

    /* TODO -> executa funcoes que estao dentro dos estados */
    public long getNumMexidas() {}
    public long getNumTentativasEsmagamento() {}
    public long getNumPicadas() {}
    public boolean isSobreAnimal() {}
    public boolean isSobreObjeto() {}
}
