#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <string.h>
#include <stdbool.h>

#define TAM 5

int main(int argc, char** argv){

    char* pt[] = {"ola", "adeus", "bola", "quadrado", "agua"};
    char* ing[] = {"hello", "goodbye", "ball", "square", "water"};
    char palavra[10];
    bool trad_check = false;

    //setbuf(stdout, NULL);
    //int fd[2];
    //pipe(fd);
    //char res[10];
    //int p = fork();

    //if(p == 0){
        //close(fd[1]);
        //int sizeR = read(fd[0], palavra, sizeof(palavra));
        //printf("\n [filho] - recebeu com o %s tamanho %d\n", res, sizeR);

        scanf(" %s", palavra);

        if(argc == 1){
            for(int i = 0; i < TAM; i++){
                if(strcmp(palavra, pt[i]) == 0){
                    printf("PT: %s\n", palavra);
                    printf("ING: %s\n\n", ing[i]);
                    trad_check = true;
                }
            }

            if(trad_check == false){
                printf("Unknown");
            }
        }
    /*}else{
        close(fd[0]);
        int sizeE = write(fd[1], palavra, 4);
        printf("\n[pai] Escreveu %s\n", palavra);
    }*/

    return 0;
}