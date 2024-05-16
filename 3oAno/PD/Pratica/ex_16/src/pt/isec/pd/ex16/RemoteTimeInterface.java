package pt.isec.pd.ex16;

//no regestry esta uma referencia remota para o

public interface RemoteTimeInterface extends java.rmi.Remote{
    public Hora getHora() throws java.rmi.RemoteException;
}
