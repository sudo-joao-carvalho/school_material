import java.io.*;
import java.net.*;
import java.util.StringTokenizer;

public class Main {
    public static final int MAX_SIZE = 256;
    public static final String TIME_REQUEST = "TIME";
    public static final int TIMEOUT = 10; //segundos

    public static void main(String[] args)
    {

        //InetAddress serverAddr = null;  facultativo para o tcp
        String hostname;
        int serverPort;
        String response;

        if(args.length != 2){
            System.out.println("Sintaxe: java UdpTimeClient serverAddress serverUdpPort");
            return;
        }

        hostname = args[0];
        serverPort = Integer.parseInt(args[1]);

        try(Socket socket = new Socket(hostname, serverPort);
            PrintStream pout = new PrintStream(socket.getOutputStream(), true);
            BufferedReader bin = new BufferedReader(new InputStreamReader(socket.getInputStream()))){ //try with resources garante-nos que quando acaba o bloco do try o recurso é fechado automaticamente

            //serverAddr = InetAddress.getByName(args[0]);
            socket.setSoTimeout(TIMEOUT*1000);

            //PrintStream pout = new PrintStream(socket.getOutputStream(), true); //ao inves de usar OutputStream usamos um decorator que ja tenha o println
            //se tiver a flag autoFlush a true ele faz flush automatico e nao é preciso fazermos nos pout.flush()
            pout.println(TIME_REQUEST);
            //pout.flush(); //Esvazia o buffer

            response = bin.readLine();

            if(response != null){ //isto é quando chega ao final do ficheiro o readLine devolve null
                System.out.println(response);
            }else{
                System.out.println("Nao houve resposta do servidor");
            }
            System.out.println("Hora indicada pelo servidor: " + response);

            //******************************************************************
            //Exemplo de como retirar os valores da mensagem de texto
            try{
                StringTokenizer tokens = new StringTokenizer(response," :");

                int hour = Integer.parseInt(tokens.nextToken().trim());
                int minute = Integer.parseInt(tokens.nextToken().trim());
                int second = Integer.parseInt(tokens.nextToken().trim());

                System.out.println("Horas: " + hour + " ; Minutos: " + minute + " ; Segundos: " + second);
            }catch(NumberFormatException e){}

            //******************************************************************

        }catch(UnknownHostException e){
            System.out.println("Destino desconhecido:\n\t"+e);
        }catch(NumberFormatException e){
            System.out.println("O porto do servidor deve ser um inteiro positivo.");
        }catch(SocketTimeoutException e){
            System.out.println("Nao foi recebida qualquer resposta:\n\t"+e);
        }catch(SocketException e){
            System.out.println("Ocorreu um erro ao nivel do socket UDP:\n\t"+e);
        }catch(IOException e){
            System.out.println("Ocorreu um erro no acesso ao socket:\n\t"+e);
        }
    }
}