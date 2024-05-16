#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <string.h>
#include <stdbool.h>

int main(int argc, char** argv){

    char letra;
    char palavra[25];

    do{
        printf("Insira uma letra: ");
        scanf(" %c", &letra);

        if(letra != 'x' && letra != 'i' && letra != 'f'){
            printf("[ERRO] Letra Invalida! (x/i/f)\n");
            continue;
        }

        int id = fork();
    
        if(letra == 'x')
            return -1;

        if(id < 0){
            printf("[ERRO] Erro na criacao do processo filho");
            return -2;
        }else if(id == 0){

            if(letra == 'i'){
                printf("Insira a palavra que pretende traduzir: ");
                scanf(" %s", palavra);
                execl("./ding", "./ding", palavra, NULL);
            }

            if(letra == 'f'){
                printf("Insira a palavra que pretende traduzir: ");
                scanf(" %s", palavra);
                execl("./dfran", "./dfran", palavra, NULL);
            }
        }else if(id > 0){
            wait(&id);
        }

    }while(letra != 'x');

    return 0;
}