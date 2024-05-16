#include <stdio.h>
#define MAX 100

int main(){
    int num, numQuad;
    char string[MAX];
    printf("Insira um numero para eu elevar ao quadrado: \n");
    scanf("%d", &num);
    numQuad = num*num;
    printf("%d e o seu numero ao quadrado.", numQuad);
    printf("\nInsira agora um conjunto de carateres para eu terminar!\n");
    scanf("%s", string);
    return 0;
}