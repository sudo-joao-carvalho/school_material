#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <unistd.h>

int main(int argc, char **argv){
	char port[10];
	char letra;
	int fd[2];
	pipe(fd);
	int pid;


    printf("Escreva uma letra (\'i\' -> ingles, \'f\'-> frances) ou \'qualquer coisa\' para terminar!\n\n>> ");
    scanf(" %c", &letra);

    if(letra == 'i' || letra == 'f'){
        printf("Escreva uma palavra PT para eu traduzir!\n\n>> ");
        scanf(" %s", port);
    }
    else{
        return 0;
    }

    pid = fork();
    if(pid < 0)
        return -1;
    else if(pid == 0){
        close(0);
        dup(fd[0]);
        close(fd[0]);
        close(fd[1]);
        switch(letra){
            case 'i':
                execl("./rding", "./rding", NULL);
                break;
            case 'f':
                execl("./rdfran", "./rdfran", NULL);
                break;
            default:
                return -1;
            }
    }
    else{
        close(fd[0]);
        write(fd[1], port, sizeof(port));
        wait(&pid);
        printf("\nPai: Vou desligar... Adeus!\n");
    }

	return 0;
}
