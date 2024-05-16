#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <sys/types.h>
#include <unistd.h>

int main(int argc, char **argv){
	char port[][10] = {"bola", "campo", "flores", "ceu", "relva"};
	char ing[][10] = {"balle", "terrain", "fleurs", "ciel", "herbe"};
	int i;

	if(argc != 2){
		printf("Introduza uma palavra que eu conheca!\n");
		return -1;
	}
	for(i = 0; i < 5; i++)
		if(strcmp(argv[1], port[i]) == 0){
			printf("A traducao da palavra %s -> %s\n", argv[1], ing[i]);
			fflush(stdout);
			return 0;
		}
	printf("Unknown\n\n");

	return 0;
}
