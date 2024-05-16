#include <stdio.h>
#include <string.h>
#define MAX 5

int main(int argc, char** argv){
    char* ding[] = {"cat", "dog", "school", "lunch", "dinner"};
    char* dport[] = {"gato", "cao", "escola", "almoco", "jantar"};

    if (argc == 1){
        printf("\nInsira uma palavra apos a execucao do programa.\n");
    }

    else if (argc > 2){
        printf("\nInseriu demasiados argumentos!\n");
    }

    else if (argc == 2){
        for (int i = 0; i < MAX; i++){
            if (strcmp(argv[1], dport[i]) == 0){
                printf("\nTraducao: %s\n", ding[i]);
                return 0;
            }
        }
                printf("\nPalavra desconhecida.\n");
    }
    return 0;
}