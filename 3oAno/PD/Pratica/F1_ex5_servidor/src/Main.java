import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Calendar;

public class Main {

    private static final int MAX_SIZE = 256;
    private static final String TIME_REQUEST = "TIME";
    public static void main(String[] args) {

        int listeningPort;
        DatagramPacket packet;
        String receivedMsg, timeMsg;
        Calendar calendar;

        if(args.length != 1)
            return ;

        listeningPort = Integer.parseInt(args[0]);

        try(DatagramSocket socket = new DatagramSocket(listeningPort)){ //try with resources(tem coisas que estao dentro dos ()) --> é melhor assim porque nao temos que fazer close do socket... se existisse uma excessao antes de chegar ao close do socket o socket nao ia ser fechado

            System.out.println("UDP Time Server iniciado...");

            while(true){

                //RECEBER MENSAGEM
                packet = new DatagramPacket(new byte[MAX_SIZE], MAX_SIZE);
                socket.receive(packet);

                //CONVERTER PARA STRING
                receivedMsg = new String(packet.getData(), 0, packet.getLength());

                System.out.println("Recebido \"" + receivedMsg + "\" de " +
                        packet.getAddress().getHostAddress() + "(" +
                            packet.getAddress().getHostName() + ")" +
                            ":" + packet.getPort());

                if(!receivedMsg.equalsIgnoreCase(TIME_REQUEST)){
                    continue;
                }

                calendar = Calendar.getInstance();
                timeMsg = calendar.get(Calendar.HOUR_OF_DAY) + ":" +
                        calendar.get(Calendar.MINUTE) + ":" + calendar.get(Calendar.SECOND);

                packet.setData(timeMsg.getBytes());
                packet.setLength(timeMsg.length());

                socket.send(packet);

            }

        }catch (Exception e){
            System.out.println(e);
        }
    }
}
