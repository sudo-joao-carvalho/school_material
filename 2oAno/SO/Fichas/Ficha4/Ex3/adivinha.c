#include <signal.h>
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <string.h>

int main(int argc, char **argv)
{
    if(argc != 2){
        printf("\nPreciso de 2 argumentos. Da-me o PID do sorteia\n");
        return 1;
    }
    int pid;
    printf("\nO meu pid [%d]\n", getpid());
    pid = atoi(argv[1]);
    union sigval val;
    while(1){
        printf("\nQual o numero?\n");
        scanf(" %d", &val.sival_int);
        if(val.sival_int == 0)
            break;
        sigqueue(pid, SIGUSR1, val);
    }
    return 0;
}
