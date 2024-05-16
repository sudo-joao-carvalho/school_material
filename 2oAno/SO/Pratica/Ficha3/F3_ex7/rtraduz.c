#include <stdio.h>
#include <sys/wait.h>
#include <unistd.h>
#include <fcntl.h>

int main(int argc, char **argv)
{

    char letra;
    char palavra[25];
    int fd[2];
    pipe(fd);

    printf("Insira uma letra: ");
    scanf(" %c", &letra);

    if (letra != 'x' && letra != 'i' && letra != 'f'){
        printf("[ERRO] Letra Invalida! (x/i/f)\n");
        return -3;
    }

    int id = fork();

    if (letra == 'x')
        return -1;

    if (id < 0){
        printf("[ERRO] Erro na criacao do processo filho");
        return -2;
    }
    else if (id == 0){
        close(0);
        dup(fd[0]);
        close(fd[0]);
        close(fd[1]);

        if (letra == 'i'){
            execl("./rding", "./rding", NULL);
        }

        if (letra == 'f'){     
            execl("./rfran", "./rfran", NULL);
        }
    }
    else if (id > 0){
        printf("Insira a palavra que pretende traduzir: ");
        scanf(" %s", palavra);
        close(fd[0]);
        write(fd[1], palavra, sizeof(palavra));
        //wait(&id);
    }

}