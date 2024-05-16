public class VooState extends MelgaAdapter{

    public VooState(MelgaContext context, MelgaData data){
        super(context, data);
    }

    @Override
    public void mexe(){
        melgaData.mexe();

        if(melgaData.isSobreAnimal()){
            changeState(EMelgaState.POUSADA);
        }else if(melgaData.isSobreObjeto()){
            changeState(EMelgaState.PRONTA_PICAR);
        }
    }

    @Override
    public boolean tentaEsmagar(){
        if(!tentaEsmagar()){
            changeState(EMelgaState.VOO);
            return false;
        }else{
            changeState(EMelgaState.MORTA);
            return true;
        }
    }

    @Override
    public EMelgaState getState(){return EMelgaState.VOO;}
}
