#include <signal.h>
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>

int main()
{
    int pid;
    printf("\nIntroduza o pid:\n");
    scanf("%d", &pid);
    union sigval valores;
    valores.sival_int = 123;
    sigqueue(pid, SIGUSR1, valores);

    return 0;
}
