#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <string.h>
#include <stdbool.h>

#define TAM 5

int main(int argc, char** argv){

    char* pt[] = {"ola", "adeus", "bola", "quadrado", "agua"};
    char* fran[] = {"bonjour", "au revoir", "balle", "carre", "eau"};
    char *palavra = argv[1];
    bool trad_check = false;


    if(argc == 1){
        printf("[ERRO] Insira uma palavra\n");
        return -1;
    }

    if(argc == 2){
        for(int i = 0; i < TAM; i++){
            if(strcmp(palavra, pt[i]) == 0){
                printf("PT: %s\n", palavra);
                printf("FR: %s\n\n", fran[i]);
                trad_check = true;
            }
        }

        if(trad_check == false){
            printf("Unknown");
        }
    }

    return 0;
}