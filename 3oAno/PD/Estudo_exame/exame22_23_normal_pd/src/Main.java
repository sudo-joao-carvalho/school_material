import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.*;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class Main {
    public Main() throws IOException, ClassNotFoundException {

        //Pratica

        DatagramSocket ds = new DatagramSocket(5001);

        //[1]
        DatagramPacket dp = new DatagramPacket(new byte[1024], 1024);
        ds.receive(dp);

        //[2]
        LocalInfo lInfo;

        try(ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(dp.getData(), 0, dp.getLength()))){
            lInfo = (LocalInfo) ois.readObject();
        }catch (Exception e){
            e.printStackTrace();
        }

        //[3]
        synchronized (subscribers){}

        //[4]
        int nSubscribers = subscribers.getLength();
        for(int i = 0; i < nSubscribers; i++){
            SubscriberInfo sInfo = subscribers.get(i);
            if(sInfo.getUserIdToFollow.equals(lInfo.getUserId())){
                try{
                    oout.writeObject(lInfo);
                }catch{
                    subscribers.remove(sInfo);
                    nSubscribers--;
                    oin.close();
                    oout.close();
                }
            }
        }

        //[5]
        Socket s = serverSocket.accept();
        ObjectInputStream oin = new ObjectInputStream(s.getInputStream());
        ObjectOutputStream oout = new ObjectOutputStream(s.getOutputStream());
        String userIdToFollow = (String) oin.readObject();

        //[6]
        synchronized (subscribers){}

        //[7]
        UdpThread udpthread;
        udpthread = new UdpThread();
        udpthread.setDaemom(true);
        udpthread.start();

        TcpThread tcpThread;
        tcpThread = new TcpThread();
        tcpThread.setDaemom(true);
        tcpThread.start();

        //[8]
        service = new RemoteService();
        String uri = "rmi://193.137.19.19:5002/live_tracking";
        Naming.bind(uri, service);

        //[9]
        tcpThread.join();

        //[10]
        public class RemoteService extends UnicastRemoteObject implements RemoteServiceInterface

        //[11]
        public RemoteService() throws RemoteException{}

        //[12]
        ref.notifyLocationInfo(lInfo);
    }

    public static void main(String[] args) {
        System.out.println("Hello world!");
    }


}

/*
* Teorica
*   1.
*       a.
*           rmi - tipo de protocolo
*           193.127.78.19 - ip do serviço remoto
*           5001 - porto de estuta do serviço remoto
*           cli_serv - nome do serviço remoto
*
*       b.
*           O método lookup é utilizado para fazer um pedido a serviço de rmi no registry num determinado url,
*           neste caso composto por  193.127.78.19, porto 5001, nome do servico cli_service
*
*           O objeto remoto recebido atraves do pedido, TCP, é serializado, ou seja, não é uma referencia direta mas sim os dados da mesma, vindo da chamada dos metodos da interface remota
*
*   2.
*       a.
*       Método HTTP         URI             Parametros opcionais
*           GET     |   /students/{year}        course
*           GET     |   /students/{id}
*           DELETE  |   /students/{id}
*
*       b.
*       ... @GetMapping("/students/{year}")
*
*       public List<Student> getStudents(... @PathVariable("year") int year,  @RequestParam(value = "course", required=false) String course)
*
*       c. A sequencia representa os dados do utilizador, ou seja, username e password, no seguinte formato, username:passowrd encriptados em Base64. Corresponde a payload do token jwt
*/