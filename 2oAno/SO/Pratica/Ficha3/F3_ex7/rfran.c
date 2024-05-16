#include <stdio.h>
#include <sys/wait.h>
#include <unistd.h>
#include <fcntl.h>
#include <string.h>
#include <stdbool.h>

#define TAM 5

int main(int argc, char** argv){

    char* pt[] = {"ola", "adeus", "bola", "quadrado", "agua"};
    char* fran[] = {"bonjour", "au revoir", "balle", "carre", "eau"};
    //char *palavra = argv[1];
    char palavra[10];
    bool trad_check = false;

    //if(argc == 1){
    //    printf("[ERRO] Insira uma palavra\n");
    //    return -1;
    //}

    //if(argc == 1){
        //printf("\n%s\n", palavra);
        scanf(" %s", palavra); //->recebe a palavra do pipe

        for(int i = 0; i < TAM; i++){
            if(strcmp(palavra, pt[i]) == 0){
                printf("PT: %s\n", palavra);
                printf("FR: %s\n\n", fran[i]);
                trad_check = true;

                return 0;
            }
        }

        if(trad_check == false){
            printf("Unknown");
            return -1;
        }
    //}

    
}