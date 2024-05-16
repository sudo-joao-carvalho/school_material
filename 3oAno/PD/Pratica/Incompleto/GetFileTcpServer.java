package pt.isec.pd.tcp.file.server;
import java.io.*;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Jose' Marinho
 */
public class GetFileTcpServer {
    public static final int MAX_SIZE = 4000;
    public static final int TIMEOUT = 10; //segundos

    public static void main(String[] args) {

        File localDirectory;
        String requestedFileName;
        String requestedCanonicalFilePath=null;
        OutputStream out;
        byte[] fileChunk = new byte[MAX_SIZE];
        int nbytes;

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

        try(ServerSocket serverSocket = /*...*/) { // 1

            while (true) {

                /*Aceita pedido de ligacao TCP em serverSocket e cria um BufferedReader para receber facilmente uma linha de texto
                  terminada com uma mudanca de linha */
                try(Socket socket = /*...*/;
                    BufferedReader bin = /*...*/) { //2 (atendimento cliente)

                    socket.setSoTimeout(TIMEOUT * 1000);
                    out = socket.getOutputStream();

                    /* Recebe o nome do ficheiro sob a forma de uma linha de texto terminada com uma mudanca de linha */
                    requestedFileName = /*...*/

                    System.out.println("Recebido pedido para \"" + requestedFileName + "\" de " + socket.getInetAddress().getHostName() + ":" + socket.getPort());

                    requestedCanonicalFilePath = new File(localDirectory + File.separator + requestedFileName).getCanonicalPath();

                    if (!requestedCanonicalFilePath.startsWith(localDirectory.getCanonicalPath() + File.separator)) {
                        System.out.println("Nao e' permitido aceder ao ficheiro " + requestedCanonicalFilePath + "!");
                        System.out.println("A directoria de base nao corresponde a " + localDirectory.getCanonicalPath() + "!");
                        continue;
                    }

                    /* Processa o pedido comecando por abrir o ficheiro solicitado para leitura */
                    try (InputStream requestedFileInputStream = /*...*/) {
                        System.out.println("Ficheiro " + requestedCanonicalFilePath + " aberto para leitura.");

                        int totalBytes=0;
                        int nChunks=0;

                        /* Enquanto não chega ao final do ficheiro, lê byte de requestedFileInputStream,
                           recorrendo ao array fileChunk, e envia-os para o cliente */
                        do {
                            nbytes = requestedFileInputStream./*...*/;

                            /*...*/

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
