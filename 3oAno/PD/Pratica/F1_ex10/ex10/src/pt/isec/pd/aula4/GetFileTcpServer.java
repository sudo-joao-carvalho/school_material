package pt.isec.pd.aula4;
import java.io.*;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Jose' Marinho
 */

class processClientThread2 implements Runnable{

    private Socket socket;
    File localDirectory;
    byte[] fileChunk = new byte[GetFileTcpServer.MAX_SIZE];
    String requestedCanonicalFilePath = null;

    public processClientThread2(Socket socket, File localDirectory){
        this.socket = socket;
        this.localDirectory = localDirectory;
    }

    @Override
    public void run(){

        try(BufferedReader bin = new BufferedReader(new InputStreamReader(socket.getInputStream()))) { //2 (atendimento cliente)

            socket.setSoTimeout(GetFileTcpServer.TIMEOUT * 1000);
            OutputStream out = socket.getOutputStream();

            String requestedFileName = bin.readLine();

            if(requestedFileName==null)
                return;

            System.out.println("Recebido pedido para \"" + requestedFileName + "\" de " + socket.getInetAddress().getHostName() + ":" + socket.getPort());

            requestedCanonicalFilePath = new File(localDirectory + File.separator + requestedFileName).getCanonicalPath();

            if (!requestedCanonicalFilePath.startsWith(localDirectory.getCanonicalPath() + File.separator)) {
                System.out.println("Nao e' permitido aceder ao ficheiro " + requestedCanonicalFilePath + "!");
                System.out.println("A directoria de base nao corresponde a " + localDirectory.getCanonicalPath() + "!");
                return;
            }

            try (InputStream requestedFileInputStream = new FileInputStream(requestedCanonicalFilePath)) {
                System.out.println("Ficheiro " + requestedCanonicalFilePath + " aberto para leitura.");

                int totalBytes=0;
                int nChunks=0;
                int nbytes;

                do {
                    nbytes = requestedFileInputStream.read(fileChunk);

                    if (nbytes > -1) {//EOF
                        out.write(fileChunk, 0, nbytes);
                        out.flush();

                        totalBytes+=nbytes; nChunks++;
                    }

                            /*try {
                                Thread.sleep(1);
                            } catch (InterruptedException ex) {}*/

                } while (nbytes > 0);

                System.out.format("Transferencia concluida em %d blocos com um total de %d bytes\r\n", nChunks, totalBytes);
            }

        } catch (SocketTimeoutException ex) { //Subclasse de IOException
            System.out.println("O cliente atual nao enviou qualquer nome de ficheiro (timeout)");
        } catch (FileNotFoundException e) {   //Subclasse de IOException
            System.out.println("Ocorreu a excepcao {" + e + "} ao tentar abrir o ficheiro " + requestedCanonicalFilePath + "!");
        } catch (IOException ex) {
            System.out.println("Problem de I/O no atendimento ao cliente atual: " + ex);
        } //try 2 (atendimento cliente)

    }
}
public class GetFileTcpServer {
    public static final int MAX_SIZE = 4000;
    public static final int TIMEOUT = 10; //segundos

    public static void main(String[] args) {

        File localDirectory;

        if (args.length != 2) {
            System.out.println("Sintaxe: java GetFileUdpServer listeningPort localRootDirectory");
            return;
        }

        localDirectory = new File(args[1].trim());

        if (!localDirectory.exists()) {
            System.out.println("A directoria " + localDirectory + " nao existe!");
            return;
        }

        if (!localDirectory.isDirectory()) {
            System.out.println("O caminho " + localDirectory + " nao se refere a uma directoria!");
            return;
        }

        if (!localDirectory.canRead()) {
            System.out.println("Sem permissoes de leitura na directoria " + localDirectory + "!");
            return;
        }

        try(ServerSocket serverSocket = new ServerSocket(Integer.parseInt(args[0]))) { // 1

            while (true) {

                Socket socket = serverSocket.accept();
                new Thread(new processClientThread2(socket, localDirectory)).start();


            } //while

        } catch (NumberFormatException e) {
            System.out.println("O porto de escuta deve ser um inteiro positivo:\n\t" + e);
        } catch (SocketException e) {
            System.out.println("Ocorreu uma excepcao ao nivel do socket UDP:\n\t" + e);
        } catch (IOException e) {
            System.out.println("Ocorreu a excepcao de E/S: \n\t" + e);
        } //try 1
    } // main    
}
