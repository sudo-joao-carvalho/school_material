package pt.isec.pd.ex17;

import java.io.IOException;

/**
 *
 * @author Jose'
 */

public interface GetRemoteFileInterface extends java.rmi.Remote{
    byte[] getFileChunk(String fileName, long offset) throws java.rmi.RemoteException;

    public long getFieSize(String fileName) throws java.rmi.RemoteException, IOException;
}
