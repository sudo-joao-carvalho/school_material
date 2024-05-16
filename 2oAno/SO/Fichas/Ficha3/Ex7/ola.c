#include <stdio.h>
#include <stdlib.h>
#include <string.h>

int main(){

    char teste[1000];
    fgets(teste, 999, stdin);
    printf("\nRecebi: [%s]\n", teste);

    return 0;
}
