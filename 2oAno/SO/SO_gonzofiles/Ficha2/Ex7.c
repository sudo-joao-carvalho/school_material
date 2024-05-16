#include <stdio.h>

int main(int argc, char** argv){
if (argc == 0)
    printf("Por favor insira uma palavra a frente da chamada da funcao!\n");


if (argc >= 1)
    for (int i = 1; i < argc; i++){
        printf("%s\n", argv[i]);
    }
}