import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

/**
 *
 * @author Jose' Marinho
 */
public class Main {
    public static final int MAX_SIZE = 4000;
    public static final int TIMEOUT = 5; //segundos

    public static void main(String[] args)
    {
        File localDirectory;
        String fileName;
        String localFilePath;
        InputStream in;
        byte []fileChunk = new byte[MAX_SIZE];
        int nbytes;
        int nLoops = 0, totalBytes=0;

        if(args.length != 4){
            System.out.println("Sintaxe: java GetFileTcpClient serverAddress serverTcpPort fileToGet localDirectory");
            return;
        }

        fileName = args[2].trim();
        localDirectory = new File(args[3].trim());

        if(!localDirectory.exists()){
            System.out.println("A directoria " + localDirectory + " nao existe!");
            return;
        }

        if(!localDirectory.isDirectory()){
            System.out.println("O caminho " + localDirectory + " nao se refere a uma directoria!");
            return;
        }

        if(!localDirectory.canWrite()){
            System.out.println("Sem permissoes de escrita na directoria " + localDirectory);
            return;
        }

        try{
            localFilePath = localDirectory.getCanonicalPath()+File.separator+fileName;
        }catch(IOException e){
            System.out.println("Ocorreu a excepcao {" + e +"} ao obter o caminho canonico para o ficheiro local!");
            return;
        }

        try(FileOutputStream localFileOutputStream = new FileOutputStream(localFilePath); /* aqui nao é preciso bufferedReader porque estamos a ler bytes */
                Socket socketToServer = new Socket(args[0],  Integer.parseInt(args[1])); /* socket para enviar ao servidor */
                PrintWriter pout = new PrintWriter(socketToServer.getOutputStream(), true)){

            socketToServer.setSoTimeout(TIMEOUT*1000);
            in = socketToServer.getInputStream();

            /* Envia para o servidor o nome do ficheiro sob a forma de uma linha de texto (terminada como mudanca de linha) */
            pout.println(fileName);
            pout.flush();

            /* Até a ligacao TCP estar encerrada, lê bytes do socket, recorrendo ao array fileChunk, e escreve-os na
               localFileOutputStream */
            while((nbytes = in.read(fileChunk)) > 0){
                nLoops++;
                totalBytes += nbytes;
                localFileOutputStream.write(fileChunk, 0, nbytes);
            }

            System.out.format("Transferencia concluida (%d ciclos num total de %d bytes)\r\n", nLoops, totalBytes);

        }catch(UnknownHostException e){
            System.out.println("Destino desconhecido:\n\t"+e);
        }catch(NumberFormatException e){
            System.out.println("O porto do servidor deve ser um inteiro positivo:\n\t"+e);
        }catch(SocketTimeoutException e){
            System.out.println("Não foi recebida qualquer bloco adicional, podendo a transferencia estar incompleta:\n\t"+e);
        }catch(SocketException e){
            System.out.println("Ocorreu um erro ao nível do socket TCP:\n\t"+e);
        }catch(IOException e){
            System.out.println("Ocorreu um erro no acesso ao socket ou ao ficheiro local " + localFilePath +":\n\t"+e);
        }

    } //main
}
