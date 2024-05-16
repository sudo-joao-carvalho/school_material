#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>

int main(int argc, char **argv){

    int N = atoi(argv[1]);
    int S = atoi(argv[2]);

    fflush(stdout);

    if (argc <= 1){
        printf("\nInsira algo depois da execucao do programa!\n");
    }

    if (argc > 2){

    for (int i = 0; i < N; i++){
        printf("\n#\n");
        setbuf(stdout, NULL);
        sleep(S);
    }

    }
    

}