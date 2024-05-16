#include <signal.h>
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <string.h>
#include <time.h>
int n = 20;
int pontuacao = 0;
int perdeu = 0;

void jogo()
{
    time_t t;

    srand((unsigned) time(&t));

    int num1 = rand() % 100;
    int num2 = rand() % 100;
    int soma = num1 + num2;
    int somaUser;
    printf("\nIntroduza o valor da conta: [%d] + [%d]\n", num1, num2);
    if(scanf("%d", &somaUser) != 1){
        perror("Nao introduziu nada -Perdeu\n");
    }
    if(soma == somaUser){
        pontuacao++;
        printf("Acertou e tem [%d] pontos\n", pontuacao);
        n--;
    }
    else{
        n--;
        printf("Perdeu\n");
        if(++perdeu == 2)
            exit(1);
        else
            printf("\nTem mais uma tentativa\n");
    }
}

void handler_sigalrm(int s, siginfo_t *i, void *v)
{
    printf("Acabou o tempo - Perdeu\n");
    exit(1);
}

int main()
{
    int i = 0;
    struct sigaction sa;
        //sa.sa_handler = handler_sigalrm;
        sa.sa_sigaction = handler_sigalrm;
        sa.sa_flags = SA_SIGINFO;
        sigaction(SIGALRM, &sa, NULL);

        while(1){
            if(i == 5){
                exit(1);
            }
            else{
                alarm(n);
                jogo();
            }
            i++;
        }
        return 0;
}
