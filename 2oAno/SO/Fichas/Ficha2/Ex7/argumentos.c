#include <stdio.h>

int main(int argc, char **argv, char **envp){
	int i;
	for(i = 1; i < argc; i++)
		printf("%do. argumento: %s\n",i, argv[i]);

	printf("Var. Ambiente:\n");
	for(i = 0; envp[i] != NULL; i++)
		printf("VAR[%d] - '%s'\n", i, envp[i]);

	return 0;
}
