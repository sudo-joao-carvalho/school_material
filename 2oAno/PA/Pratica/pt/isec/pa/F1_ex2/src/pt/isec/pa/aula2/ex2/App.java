package pt.isec.pa.aula2.ex2;

import java.util.Scanner;
public class App {

    public static final int NUMERO_PENSADO_IGUAL = 1;
    public static final int NUMERO_PENSADO_MAIOR = 2;
    public static final int NUMERO_PENSADO_MENOR = 3;

    public static int obtemResposta(int palpite){

        Scanner sc = new Scanner(System.in);
        int resposta;

        do{
            System.out.printf("O numero em que pensou e: %d\n", palpite);
            System.out.println("\n" + NUMERO_PENSADO_IGUAL + " - SIM\n" + NUMERO_PENSADO_MAIOR + " - PENSEI NUM NUMERO MAIOR\n" + NUMERO_PENSADO_MENOR + " - PENSEI NUM NUMERO MENOR\n");
            System.out.printf("\nResposta: ");

            resposta = sc.nextInt();
        }while(resposta < 1|| resposta > 3);

        return resposta;

    }
    public static void main(String[] args) {
        int min = 1, max = 100, palpite;
        int resposta, numTentativas = 0;

        System.out.println("Pense num número...");

        do{

            numTentativas++;
            palpite = (min + max)/2;

            resposta = obtemResposta(palpite);

            if(resposta == NUMERO_PENSADO_MAIOR)
                min = palpite + 1;
            else if(resposta == NUMERO_PENSADO_MENOR)
                max = palpite - 1;

        }while(resposta != NUMERO_PENSADO_IGUAL && min <= max);

        if(resposta == NUMERO_PENSADO_IGUAL){
            System.out.printf("Acertei no numero em %d tentativas", numTentativas);
        }
    }
}