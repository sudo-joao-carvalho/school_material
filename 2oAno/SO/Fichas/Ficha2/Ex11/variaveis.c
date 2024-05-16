#include <stdio.h>
#include <stdlib.h>

int main(int argc, char **argv, char **envp){
	char * var;

	if(argc != 2){
		perror("O numero de argumentos tem de ser 2!\n");
		return -1;
	}
	else{
		var = getenv(argv[1]);
		printf("%s\n", var);
		return 0;
	}
}
