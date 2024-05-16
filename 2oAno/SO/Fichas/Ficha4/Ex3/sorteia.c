#include <signal.h>
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <string.h>
#include <time.h>

int adivinhado = 0;
int num;

void termina(int sign)
{
    printf("\nAcertou %d numeros! Adeus...\n", adivinhado);
    exit(0);
}

void sorteia()
{
    srand(time(NULL));
    num = (rand() % 100) + 1;
}

void verifica(int sign, siginfo_t *info, void *old)
{
    int num_recebido = info->si_value.sival_int;
    int pid = info->si_pid;
    printf("\nRecebi o valor '%d' do PID[%d]!\n", num_recebido, pid);

    if(num_recebido > num)
        printf("\ndemasiado grande\n");
    else if(num_recebido < num)
        printf("\ndemasiado pequeno\n");
    else if(num_recebido == num){
        printf("\nacertou\n");
        ++adivinhado;
        sorteia();
    }
}

int main(int argc, char **argv)
{
    printf("\nO meu pid [%d]\n", getpid());
    struct sigaction sa;
        sa.sa_sigaction = verifica;
        sa.sa_flags = SA_SIGINFO;
        sigaction(SIGUSR1, &sa, NULL);

    struct sigaction sa_int;
        sa_int.sa_handler = termina;
        sigaction(SIGINT, &sa_int, NULL);

    sorteia();
    while(1){
    }

    return 0;
}
