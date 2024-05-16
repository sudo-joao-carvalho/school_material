package pt.isec.pd.ex16;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class Client {

    public static void main(String[] args) {
        System.out.println("Client RMI");

        //RemoteTimeInterface ref

        try{
            String registryName = "timeserver";
            String ip = "localhost";

            if(args.length >= 1){
                ip = args[0];
            }

            RemoteTimeInterface ref = (RemoteTimeInterface) Naming.lookup("rmi://" + ip + "/" + registryName);

            System.out.println(ref.getHora());

        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (NotBoundException e) {
            throw new RuntimeException(e);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }

    }
}
