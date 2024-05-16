#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <sys/types.h>
#include <unistd.h>

int main(int argc, char **argv){
	char port[][10] = {"bola", "campo", "flores", "ceu", "relva"};
	char ing[][10] = {"ball", "field", "flowers", "sky", "grass"};
	char res[10];
	int i;

	fgets(res,9,stdin);
	//res[strlen(res) - 1] = '\0';

	for(i = 0; i < 5; i++)
		if(strcmp(res, port[i]) == 0){
			printf("A traducao da palavra [%s] -> [%s]\n", res, ing[i]);
			fflush(stdout);
			return 0;
		}
	printf("Unknown\n\n");

	return 0;
}
