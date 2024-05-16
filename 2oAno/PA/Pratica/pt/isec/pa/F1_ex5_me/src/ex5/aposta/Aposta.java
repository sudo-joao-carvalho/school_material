package ex5.aposta;

import java.util.Scanner;

public class Aposta {

    public static final int TAM = 6;

    public static final int NUMERO = 1;

    public static final int ESTRELA = 2;

    public static final int LISTA = 3;
    public static final int APOSTAR = 4;

    private int[] chave_final;

    private int estrela;
    private int nInseridos = 0;
    private boolean checkEstrela = false;

    public Aposta(){
        chave_final = new int[TAM];
    }
    public void preencheChave(){

        Scanner sc = new Scanner(System.in);
        int aposta;

        System.out.printf("Insira um numero compreendido entre 1 e 49\n");
        aposta = sc.nextInt();

        if(aposta < 1 || aposta > 49){
            System.out.printf("[ERRO] Numero inserido nao esta compreendido entre 1 e 49\n");
            return ;
        }

        chave_final[nInseridos] = aposta;
        nInseridos++;

    }
    public boolean verificaChaveFinal(){

        if(nInseridos < TAM - 1){
            return false;
        }

        if(nInseridos == TAM - 1){
            if(checkEstrela){
                System.out.printf("Chave Final completa");
                return true;
            }
        }

        return false;
    }

    public void printaAposta(){
        System.out.printf("[ ");
        for(int i = 0; i < nInseridos; i++){
            System.out.printf("%d ", chave_final[i]);
        }
        System.out.printf("]\n\n");

        if(checkEstrela){
            System.out.printf("Estrela: \n%d", estrela);
        }else
            System.out.printf("\nEstrela nao introduzida\n");
    }
    public void respostaUser(){

        Scanner sc = new Scanner(System.in);
        int resposta;

        do{

            System.out.printf("%d - NUMERO\n", NUMERO);
            System.out.printf("%d - ESTRELA\n", ESTRELA);
            System.out.printf("%d - LISTAR APOSTA\n", LISTA);
            System.out.printf("%d - APOSTAR\n", APOSTAR);
            resposta = sc.nextInt();

            if(resposta == NUMERO){
                if(!verificaChaveFinal()){
                    preencheChave();
                }
            }

            if(resposta == ESTRELA){
                if(!checkEstrela){
                    System.out.printf("Insira uma estrela entre 1 e 13\n");
                    estrela = sc.nextInt();
                    if(estrela >= 1 || estrela <= 13){
                        chave_final[5] = estrela;
                        nInseridos++;
                    }else
                        System.out.printf("[ERRO] Chave tem que estar compreendida entre 1 e 13\n");

                }else
                    System.out.printf("Estrela ja esta preenchida\n");

            }

            if(resposta == LISTA){
                printaAposta();
            }

        }while(resposta != APOSTAR);

        if(resposta == APOSTAR)

            System.out.printf("\nAPOSTA FEITA");
    }

}
