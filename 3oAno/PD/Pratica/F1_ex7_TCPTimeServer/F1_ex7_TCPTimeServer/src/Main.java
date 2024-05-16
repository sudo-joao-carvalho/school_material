import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Calendar;

public class Main {

    public static final int MAX_SIZE = 256;
    public static final String TIME_REQUEST = "TIME";
    public static void main(String[] args) {

        int listeningPort;
        String receivedMsg, timeMsg;
        Calendar calendar;

        if(args.length != 1){
            System.out.println("Sintaxe: java UdpTimeServer listeningPort");
            return;
        }

        listeningPort = Integer.parseInt(args[0]);

        try(ServerSocket serverSocket = new ServerSocket(listeningPort)){

            System.out.println("TCP Time Server Running");

            while(true){

                try(Socket socket = serverSocket.accept(); /* aceitar o cliente */
                    BufferedReader bin = new BufferedReader(new InputStreamReader(socket.getInputStream())); /* buffered reader para ler o que se recebeu no socket */
                    PrintStream pout = new PrintStream(socket.getOutputStream(), true);){ /* print stream para escrever com println no socket */

                    if((receivedMsg = bin.readLine()) == null){
                        continue;
                    }

                    System.out.println("Recebido \"" + receivedMsg + "\" de " +
                            socket.getInetAddress().getHostAddress() + ":" + socket.getPort());

                    if(!receivedMsg.equalsIgnoreCase(TIME_REQUEST)){
                        continue;
                    }

                    calendar = Calendar.getInstance();
                    timeMsg = calendar.get(Calendar.HOUR_OF_DAY)+":"+
                            calendar.get(Calendar.MINUTE)+":"+calendar.get(Calendar.SECOND);

                    pout.println(timeMsg); //escreve no socket
                }catch (Exception e){
                    System.out.println(e);
                }
            }

        }catch (Exception e){
            System.out.println(e);
        }
    }
}