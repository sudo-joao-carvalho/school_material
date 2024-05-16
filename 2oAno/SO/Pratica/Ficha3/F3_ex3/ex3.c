#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>

int main(int argc, char **argv){

    int N = atoi(argv[1]);    
    int S = atoi(argv[2]);

    if(argc <= 2){
        printf("[ERRO] Numero de argumentos invalido -> Formato: ./ programa N S\n");
        return -1;
    }

    if(argc == 3){
        for(int i = 0; i < N; i++){
            printf("*");
            setbuf(stdout, NULL);
            sleep(S);
        }
            
        return 0;
    }

}