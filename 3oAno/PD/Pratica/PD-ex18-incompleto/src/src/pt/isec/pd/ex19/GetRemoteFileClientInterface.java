package pt.isec.pd.ex19;

/**
 *
 * @author Jose'
 */

/*

* Interface RMI realizada pelos clientes (GetRemoteFileClientInterface) e que deve
* incluir o metodo:
*
*       void writeFileChunk(byte [] fileChunk, int nbytes) throws java.io.IOException.
*/ 

public interface GetRemoteFileClientInterface extends java.rmi.Remote {
    void writeFileChunk(byte [] fileChunk, int nbytes) throws java.io.IOException, java.rmi.RemoteException;
}
