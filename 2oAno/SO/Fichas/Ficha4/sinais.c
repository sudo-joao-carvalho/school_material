#include <signal.h>
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>

/* void funcSignal(int sign)
 * {
 *     printf("\nRecebi o sinal %d\n", sign);
 *     exit(1);
 * }
 */

void funcSignal(int sign, siginfo_t *info, void *old)
{
    printf("\nRecebi o sinal [%d]\n", sign);
    printf("\nO autor do envio foi o [%d] pid", info->si_pid);
    printf("\nO valor recebido foi [%d]\n", info->si_value.sival_int);
    exit(1);
}

int main()
{
    int i;
    printf("\nO meu pid [%d]\n", getpid());
    struct sigaction sa;
        //sa.sa_handler = funcSignal;
        sa.sa_sigaction = funcSignal;
        sa.sa_flags = SA_SIGINFO;
        //sigaction(SIGINT, &sa, NULL);
        sigaction(SIGUSR1, &sa, NULL);
        scanf("%d", &i);

        return 0;
}
