package pt.isec.pd.aula4;

import pt.isec.pd.aula5.time.Time;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Calendar;
import java.util.GregorianCalendar;

//PARA TORNAR O SERVIDOR CONCORRENTE, SEMPRE QUE RECEBE UM UTILIZADOR LANÇA-SE UMA THREAD PARA TRATAR DESSE UTILIZADOR

class processClientThread extends Thread{
    private Socket toClientSocket;

    public processClientThread(Socket toClientSocket){
        this.toClientSocket = toClientSocket;
    }

    @Override
    public void run(){
        try(ObjectOutputStream oout = new ObjectOutputStream(toClientSocket.getOutputStream());
            ObjectInputStream oin = new ObjectInputStream(toClientSocket.getInputStream())){

            toClientSocket.setSoTimeout(TcpSerializedTimeServer.TIMEOUT);

            String request = (String)oin.readObject();

            if(request == null){ //EOF
                return;
            }

            System.out.println("[ " + this.getName() /* isto so da por ser extends thread, se fosse implements runnable ja nao dava, tinha que ser Thread.currentThread().getName() */ + " ]" + "Recebido \"" + request.trim() + "\" de " +
                    toClientSocket.getInetAddress().getHostAddress() + ":" +
                    toClientSocket.getPort());

            if(!request.equalsIgnoreCase(TcpSerializedTimeClient.TIME_REQUEST)){
                System.out.println("Unexpected request");
                return;
            }

                    /*try{
                        //simula processamento de 10 segundos
                        Thread.sleep(10000);
                    } catch (InterruptedException ex) {}*/

            Calendar calendar = GregorianCalendar.getInstance();

            // ou oout.writeUnshared caso o obj seja alterado multiplas vezes
            oout.writeObject(new Time(calendar.get(GregorianCalendar.HOUR_OF_DAY),
                    calendar.get(GregorianCalendar.MINUTE),
                    calendar.get(GregorianCalendar.SECOND)));

            oout.flush();

        }catch(IOException | ClassNotFoundException e){
            System.out.println("Problema na comunicacao com o cliente \n\t" + e);
        }
    }
}

public class TcpSerializedTimeServer{

    public static final String TIME_REQUEST = "TIME";
    public static final int TIMEOUT = 5000; // msec
    
    public static void main(String args[]){
        
        if(args.length != 1){
            System.out.println("Sintaxe: java TcpSerializedTimeServerIncomplete listeningPort");
            return;
        }
        
        try(ServerSocket socket = new ServerSocket(Integer.parseInt(args[0]))){

            System.out.println("TCP Time Server iniciado no porto " + socket.getLocalPort() + " ...");

            while(true){

                Socket toClientSocket = socket.accept();
                new processClientThread(toClientSocket).start();
                
            }
            
        }catch(NumberFormatException e){
            System.out.println("O porto de escuta deve ser um inteiro positivo.");
        }catch(IOException e){
            System.out.println("Ocorreu um erro ao nivel do socket de escuta:\n\t"+e);
        }
    }
           
}