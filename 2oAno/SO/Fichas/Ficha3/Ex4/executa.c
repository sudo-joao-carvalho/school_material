#include <stdio.h>
#include <string.h>
#include <unistd.h>


int main(){
	char s[5], prog[7] = "./";
	char port[5] = "port", ing[4] = "ing";
	int resPort, resIng;

	printf("Digite \"port\" ou \"ing\" consoante que a mensagem em PT ou ENG, respetivamente:\n");
	scanf(" %[^\n]", s);

	resPort = strncmp(s, port, 4);
	resIng = strncmp(s, ing, 3);


	if(resPort == 0 || resIng == 0){
		strcat(prog, s);
		execl(prog, prog, NULL);
	}
	else
		return -1;

	printf("Missao Cumprida!\n");
	return 0;
}
