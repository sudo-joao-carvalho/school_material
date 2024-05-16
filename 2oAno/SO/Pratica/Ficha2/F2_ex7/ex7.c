#include <stdio.h>

int main(int argc, char** argv){

    if(argc == 1){
        printf("Por favor insira uma palavra a frente da execucao do programa!\n");
        return 0;
    }

    if(argc >= 1){
        for(int i = 0; i < argc; i++){
            printf("%s\n", argv[i]);
        }
    }

    return 0;
}