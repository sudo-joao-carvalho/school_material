public enum EMelgaState {

    VOO, POUSADA, PRONTA_PICAR, MORTA;

    //FABRICA DE OBJETOS

    public IStates criaMelgaState(MelgaContext context, MelgaData data){
        return switch (this){
            case VOO -> new VooState(context, data);
            case POUSADA -> new PousadaState(context, data);
            case PRONTA_PICAR -> new ProntaPicarState(context, data);
            case MORTA -> new MortaState(context, data);
        }
    }
}
