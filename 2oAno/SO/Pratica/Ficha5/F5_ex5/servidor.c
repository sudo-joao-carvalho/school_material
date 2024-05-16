#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include <sys/stat.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <errno.h>
#include <fcntl.h>
#include <signal.h>
#include <sys/select.h>

#define SERVER_FIFO "SERVIDOR1"
#define CLIENT_FIFO "ClIENTE%d"

char CLIENT_FIFO_FINAL[100];
//mensagem que recebe
//sprintf(CLIENT_FIFO_FINAL, CLIENTE_FIFO, mensagemRecebida.pid);

typedef struct
{
    pid_t pid;
    char operador;
    int num1;
    int num2;
}dataMSG;

typedef struct{
    float res;
}dataRPL;

void closeServer(){
    unlink(SERVER_FIFO);
    printf("\nadeus\n");
    exit(1);
}

void funcExit(int s, siginfo_t *i, void *v){
    closeServer();
}

void constroiNomeFifo(char* name, int pid){
    sprintf(CLIENT_FIFO_FINAL, CLIENT_FIFO, pid);
}

int main(){

    int nfd;
    fd_set read_fds;
    struct timeval tv;
    char varSair[100];
    struct sigaction sa;
        sa.sa_sigaction = funcExit;
    sigaction(SIGINT, &sa, NULL);

    dataMSG mensagemRecebida;
    dataRPL resposta;

    if(mkfifo(SERVER_FIFO, 0666) == -1){
        if(errno == EEXIST){
            printf("Servidor em execucao ou fifo ja existe");
            return -1;
        }
        printf("Erro ao abrir o fifo");
        return -1;
    }

    int fdRecebe = open(SERVER_FIFO, O_RDWR);
    
    if(fdRecebe == -1){
        printf("Erro");
        return -1;
    }

    do{
        tv.tv_sec = 5;
        tv.tv_usec = 0;
        FD_ZERO(&read_fds);
        FD_SET(0, &read_fds);
        FD_SET(fdRecebe, &read_fds);

        nfd = select(fdRecebe + 1, &read_fds, NULL, NULL, &tv);

        if(FD_ISSET(0, &read_fds)){
            fgets(varSair, 99, stdin);
            if(strcmp(varSair, "encerra\n") == 0)
                closeServer();
            else
                printf("Comando Invalido");
        }

        if(FD_ISSET(fdRecebe, &read_fds)){
            int size = read(fdRecebe, &mensagemRecebida, sizeof(mensagemRecebida));

            if(size > 0){
                if(mensagemRecebida.operador == '+'){
                    resposta.res = mensagemRecebida.num1 + mensagemRecebida.num2;
                }else if(mensagemRecebida.operador == '.')
                    kill(getpid(), SIGINT);

                sprintf(CLIENT_FIFO_FINAL, CLIENT_FIFO, mensagemRecebida.pid);
                int fdEnvio = open(CLIENT_FIFO_FINAL, O_WRONLY);
                int size2 = write(fdEnvio, &resposta, siszeof(resposta));
                close(fdEnvio);
            }
        }
    }while(1);

}
