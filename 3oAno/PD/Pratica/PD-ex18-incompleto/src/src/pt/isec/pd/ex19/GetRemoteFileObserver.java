package pt.isec.pd.ex19;

import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;


//backup server
public class GetRemoteFileObserver extends UnicastRemoteObject implements GetRemoteFileObserverInterface {

    public GetRemoteFileObserver() throws RemoteException{}

    @Override
    public void notifyNewOperationConcluded(String description) throws RemoteException{
        System.out.println("-> " + description);
        System.out.println();
    }

    public static void main(String[] args) {
        try {
            if (args.length < 2) {
                System.out.println("Deve passar 2 argumentos: ");
                System.out.println("       1 - endereço do RMI registry onde esta registado o serviço");
                System.out.println("       2 - endereço IP da interface da rede que deve ser incluido na ....\n");
            }

            String objectUrl = "rmi://" + args[0] + "/servidor-ficheiros-pd";

            GetRemoteFileServiceInterface getRemoteFileService = (GetRemoteFileServiceInterface) Naming.lookup(objectUrl);

            System.setProperty("java-rmi.server.hostname", args[1]);

            GetRemoteFileObserver observer = new GetRemoteFileObserver();

            System.out.println("Serviço GetRemoteFileObserver criado e em execucção...\n");

            getRemoteFileService.addObserver(observer);

            System.out.println("A espera para terminar...\n");

            System.out.println();

            System.in.read();

            getRemoteFileService.removeObserver(observer);

            // terminar o serviço
            UnicastRemoteObject.unexportObject(observer, true);

        } catch (RemoteException e) {
            throw new RuntimeException(e);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (NotBoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {

        }
    }
}
