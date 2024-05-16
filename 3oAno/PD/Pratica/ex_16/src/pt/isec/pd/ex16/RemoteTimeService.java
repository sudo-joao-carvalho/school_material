package pt.isec.pd.ex16;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.ServerNotActiveException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Calendar;

public class RemoteTimeService extends UnicastRemoteObject implements RemoteTimeInterface {

    public RemoteTimeService() throws RemoteException{}

    @Override
    public Hora getHora() throws RemoteException{
        try{
            System.out.println("Mais uma invocacao...(" + getClientHost() /*metodo da class Unicast*/ + ") tratada pela thread " + Thread.currentThread().getName());
        }catch (ServerNotActiveException ex){
            System.out.println("Mais uma invocacao... (local) tratada pela thread main");
        }

        Calendar c = Calendar.getInstance();

        int horas = c.get(Calendar.HOUR_OF_DAY);
        int minutos = c.get(Calendar.MINUTE);
        int segundos = c.get(Calendar.SECOND);

        return new Hora(horas, minutos, segundos);

    }

    static public void main(String []args){

        if(args.length > 0){
            System.setProperty("java.rmi.server.hostname", args[0]);
        }

        try{

            try{
                LocateRegistry.createRegistry(Registry.REGISTRY_PORT);
                System.out.println("Registry lançado");
            }catch (RemoteException e){
                System.out.println("Registry provavelmente ja em execucao");
            }

            //cria e lanca servico

            RemoteTimeService timeService = new RemoteTimeService();

            //porque que o main chega ao fim
            //-> cria e lanca uma thread em modo utilizador que nao termina

            System.out.println("Servico RemoteTime criado e em execucao (" + timeService.getRef() + ")");

            Naming.bind("rmi://localhost/timeserver", timeService);

            System.out.println("Servico RemoteTimeService registado no registry local com o nome");

            System.out.println("Hora local: " + timeService.getHora());
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (AlreadyBoundException e) {
            throw new RuntimeException(e);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }
}
