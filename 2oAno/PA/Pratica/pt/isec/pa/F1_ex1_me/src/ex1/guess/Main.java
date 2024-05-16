package ex1.guess;

import java.util.Scanner;

public class Main {

    public static final int number = (int)(Math.random() * 100);
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int palpite;

        System.out.printf("Escreva un numero: \n");

        do{
            palpite = sc.nextInt();

            if(palpite > number){
                System.out.printf("O numero que eu escolhi é mais pequeno que esse\n");
            } else if (palpite < number) {
                System.out.printf("O numero que eu escolhi e maior que esse\n");
            }
        }while(palpite != number);


        System.out.printf("Acertou, o numero era %d", number);

    }
}