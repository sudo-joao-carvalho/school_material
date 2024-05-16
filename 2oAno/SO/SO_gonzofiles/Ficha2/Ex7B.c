#include <stdio.h>

int main(int argc, char** argv){
    char* p = argv[1];
    int i = 1;

    if (p == NULL){
        printf("\nPor favor insira uma palavra apos a execucao do programa!\n");
        return -1;
    }

    while (p != NULL){
        printf("%s\n", p);
        p = argv[++i];
    }

    return 0;
}