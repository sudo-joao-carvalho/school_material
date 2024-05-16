#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>

int main(){
    int descritor[2];
    pipe(descritor);
    int pid = fork();
    if(pid == 0){
        close(0);
        dup(descritor[0]);
        close(descritor[0]);
        close(descritor[1]);
        execl("./ola", "ola", NULL);
    }
    else if(pid > 0){
        close(descritor[0]);
        write(descritor[1], "aula de so", 10);
    }

    return 0;
}
