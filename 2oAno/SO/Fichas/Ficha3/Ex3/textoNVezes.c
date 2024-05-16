#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>

int main(int argc, char **argv){
	int num, tempo, i;

	if(argc != 4){
		perror("Para executar o programa, introduza 4 argumentos!\n");
		return -1;
	}

	num = atoi(argv[1]);
	tempo = atoi(argv[3]);

	printf("INICIO...\n");
	setbuf(stdout, NULL);

	for(i = 0; i < num; i++){
		printf("%s ", argv[2]);
		sleep(1);
	}

	printf("\nFIM...\n");
	return 0;
}
