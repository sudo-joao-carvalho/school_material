#include <stdio.h>

int main(){
	int num;
	char * palavra;
	printf("Introduza um numero: ");
	scanf("%d", &num);
	printf("o quadrado de %d e: %d\n\n", num, num*num);

	printf("Escreva qualquer coisa: ");
	scanf("%s", palavra);
	printf("Obrigado!\n");

	return 0;
}
