#include <signal.h>
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <string.h>

static int conta = 0;

void funcSignal(int sign)
{
    if(++conta == 5){
        printf("\nok, pronto!\n");
        exit(1);
    }
    printf("\nai...\n");
}

int main()
{
    char nome[50];
    struct sigaction sa;
        sa.sa_handler = funcSignal;
        //sa.sa_sigaction = funcSignal;
        sa.sa_flags = SA_SIGINFO;
        sigaction(SIGINT, &sa, NULL);
        //sigaction(SIGUSR1, &sa, NULL);
    do{
        printf("\nQual o seu nome?\n>> ");
        if(scanf(" %s", nome) != 1)
            perror("Nao introduziu um nome!\n");
        if(strcmp(nome, "sair") != 0)
            printf("\nola %s\n", nome);
    }
    while(strcmp(nome, "sair") != 0);

    return 0;
}
