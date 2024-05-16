#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <unistd.h>

int main(int argc, char **argv){
	char port[10];
	char letra;
	int i;

	do{
		printf("Escreva uma letra (\'i\' -> ingles, \'f\'-> frances) ou \'x\' para terminar!\n\n>> ");
		scanf(" %c", &letra);

		switch(letra){
		case 'x':
			printf("Vou desligar... Adeus!\n");
			return 0;
		default:
			printf("Escreva uma palavra PT para eu traduzir!\n\n>> ");
			scanf(" %s", port);
			i = fork();
			if(i == 0){
				switch(letra){
				case 'i':
					execl("./ding", "./ding", port, NULL);
					break;
				case 'f':
					execl("./dfran", "./dfran", port, NULL);
					break;
				default:
					return -1;
				}
			return 0;
			}
			else{
				wait(&i);
			}
		}
	}while(letra != 'x');

	return 0;
}
