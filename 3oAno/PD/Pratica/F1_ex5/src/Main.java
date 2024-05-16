import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Main {

    //DATAGRAM PACKET ( CARTA )
    //  -> port
    //  -> address -----> INetAdress
    //  -> data
    //  -> length
    public static void main(String[] args) {

        if(args.length != 2)
            return ;

        try(DatagramSocket socket = new DatagramSocket()){  //try with resources(tem coisas que estao dentro dos ()) --> é melhor assim porque nao temos que fazer close do socket... se existisse uma excessao antes de chegar ao close do socket o socket nao ia ser fechado
            //ENDEREÇO DE DESTINO
            String msg = "TIME";

            InetAddress servAddr = InetAddress.getByName(args[0]); //destino
            int servPort = Integer.parseInt(args[1]); //porto de destino

            //MENSAGEM (CARTA)
            DatagramPacket pkt =
                    new DatagramPacket(msg.getBytes(), msg.length(), servAddr, servPort); //numero de bytes e tamanho da mensagem

            //ENVIAR E RECEBER MENSAGEM
            //DatagramSocket socket = new DatagramSocket();

            socket.setSoTimeout(1000);

            socket.send(pkt);

            //PARA RECEBER
            pkt = new DatagramPacket(new byte[100], 100);
            socket.receive(pkt);

            String response = new String(pkt.getData(), 0, pkt.getLength());

            System.out.println(response);
            //socket.close();

        }catch (Exception e){
            System.out.println(e);
        }
    }
}