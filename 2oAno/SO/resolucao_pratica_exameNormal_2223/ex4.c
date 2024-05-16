#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <fcntl.h>
#include <sys/types.h>
#include <unistd.h>
#include <sys/stat.h>
#include <signal.h>
#include <errno.h>
#include <pthread.h>
#include <sys/select.h>

#define EQUIPA "EQUIPA_%d"
#define MAX_EQUIPAS 4
#define TAM_MAX 1000

typedef struct Equipa{
    char nome_equipa[TAM_MAX];
    int pid;
    char msg[TAM_MAX];
    char mvp_equipa_nome[TAM_MAX];
    int mvp_pontos;
    int eliminatoria;
}Equipa;

typedef struct Mensagem{
    char msg[TAM_MAX];
}Mensagem;

typedef struct Campeonato{
    int isProva_running;
    Equipa equipas[4];
    char mvp_nome[TAM_MAX];
    int eliminatoria; //2- meia final, 1- final, 0- se eliminado, 10- se venceu
    int tempo;
}Campeonato;

int numEquipas = 0;
int servidor_fd;
int equipa_fd;
char SERVIDOR_FIFO[TAM_MAX];
char EQUIPA_FIFO_FINAL[TAM_MAX];


Campeonato* verificaMVP(Campeonato* camp){

    Campeonato aux;

    for(int i = 0; i < 4; i++){
        for(int j = i + 1; j < 4; j++){
            if(camp->equipas[j].mvp_pontos > camp->equipas[i].mvp_pontos){
                strcpy(aux.mvp_nome, camp->equipas[j].mvp_equipa_nome);
            }else{
                strcpy(aux.mvp_nome, camp->equipas[i].mvp_equipa_nome);
            }
        }
    }

    return &aux;
}

void enviaMensagemDeComeco(Campeonato* camp){

    Mensagem mensagem;
    strcpy(mensagem.msg, "Comecou o campeonato\n");

    for(int i = 0; i < 4; i++){

        sprintf(EQUIPA_FIFO_FINAL, EQUIPA, camp->equipas[i].pid);
        equipa_fd = open(EQUIPA_FIFO_FINAL, O_WRONLY);

        write(equipa_fd, &mensagem, sizeof(mensagem));
        close(equipa_fd);
    }
}

void enviaMensagemDeFinal(Campeonato* camp){

    Mensagem mensagem;
    strcpy(mensagem.msg, "Campeonato finalizado. Vencedor : ");

    if(camp->tempo == 40){
        strcpy(mensagem.msg, "Campeonato finalizado [atingiu tempo maximo]");

        for(int i = 0; i < 4; i++){
            sprintf(EQUIPA_FIFO_FINAL, EQUIPA, camp->equipas[i].pid);
            equipa_fd = open(EQUIPA_FIFO_FINAL, O_WRONLY);

            write(equipa_fd, &mensagem, sizeof(mensagem));
            close(equipa_fd);
        }
    }

    for(int i = 0; i < 4; i++){
        if(camp->equipas[i].eliminatoria == 10){
            strcat(mensagem.msg, camp->equipas[i].nome_equipa);
        }
    }

    for(int i = 0; i < 4; i++){ 
        sprintf(EQUIPA_FIFO_FINAL, EQUIPA, camp->equipas[i].pid);
        equipa_fd = open(EQUIPA_FIFO_FINAL, O_WRONLY);

        write(equipa_fd, &mensagem, sizeof(mensagem));
        close(equipa_fd);
    }

}

void resetaEquipas(){
    //funcao que resetaEquipas (nao implementado)
}

void commandServidor(Campeonato* camp, char *command){

    Mensagem mensagem;
    strcpy(mensagem.msg, "Campeonato Fechou\n");

    if(strcmp(command, "encerra") == 0){

        for(int i = 0; i < 4; i++){
            sprintf(EQUIPA_FIFO_FINAL, EQUIPA, camp->equipas[i].pid);
            equipa_fd = open(EQUIPA_FIFO_FINAL, O_WRONLY);

            write(equipa_fd, &mensagem, sizeof(mensagem));
            close(equipa_fd);
        }

        free(camp);

        close(servidor_fd);
        close(equipa_fd);
        unlink(SERVIDOR_FIFO);

        exit(0);
    }

    if(strcmp(command, "ponto") == 0){

        printf("MVP: %s", camp->mvp_nome);

        for(int i = 0; i < 4; i++){
            if(strcmp(camp->mvp_nome, camp->equipas[i].mvp_equipa_nome) == 0){
                printf("Pontos: %d", camp->equipas[i].mvp_pontos);
            }
        }

    }

}

int main(int argc, char** argv){

    Campeonato *camp = malloc(sizeof(camp));
    camp->isProva_running = 0;
    camp->tempo = 0;
    Equipa aux;
    Mensagem mensagem;

    int nfd;
    fd_set read_fds;
    struct timeval tv;

    char command[TAM_MAX];

    strcpy(SERVIDOR_FIFO, stoi(getenv("GESTORNP")));

    if(mkfifo(SERVIDOR_FIFO, 0666) == -1){ //criar o fifo
        if(errno == EEXIST){ //verificar se ja existe
            printf("Servidor ja existe\n");
            return -1;
        }

        printf("Erro na criacao do fifo\n");
        return -1;
    }

    servidor_fd = open(SERVIDOR_FIFO, O_RDWR); //abrir o pipe para leitura
    if(servidor_fd == -1){
        printf("Erro ao abrir o fifo do servidor");
        exit(EXIT_FAILURE);
    }

    while(1){
        tv.tv_sec = 5;
        tv.tv_usec = 0;

        FD_ZERO(&read_fds);
        FD_SET(0, &read_fds);
        FD_SET(servidor_fd, &read_fds);

        nfd = select(servidor_fd + 1, &read_fds, NULL, NULL, &tv);
        if(nfd == -1){ //ERRO
            printf("Nada a receber\n");
            exit(0);
        }

        if(nfd == 0){ //mensagem displayed enquanto n recebe nada
            printf("A espera\n");
        }

        if(FD_ISSET(0, &read_fds)){
            fgets(command, TAM_MAX, stdin);
            commandServidorr(camp, command);
        }

        if(FD_ISSET(servidor_fd, &read_fds)){

            if(camp->isProva_running == 0){
                int size1 = read(servidor_fd, &aux, sizeof(aux)); // recebe a equipa
                if(size1 < 0){
                    printf("Erro ao receber do pipe\n");
                    exit(EXIT_FAILURE);
                }
                camp->equipas[numEquipas] = aux;
                aux.eliminatoria = 2;
                numEquipas++;   
            }

            if(numEquipas == 4 && camp->isProva_running == 0){
                camp->isProva_running = 1;
                camp->eliminatoria = 2;
                //mandar mensagem para a equipa a dizer que ja esta a decorrer o campeonato e para entrar mais tarde
                sprintf(EQUIPA_FIFO_FINAL, EQUIPA, aux.pid);
                equipa_fd = open(EQUIPA_FIFO_FINAL, O_WRONLY);
                if(equipa_fd == -1){
                    printf("ERRO ao abrir fifo da equipa\n");
                    exit(EXIT_FAILURE);
                }

                strcpy(mensagem.msg, "Campeonato ja esta a decorrer. Por favor, tente mais tarde\n");

                int size2 = write(equipa_fd, &mensagem, sizeof(mensagem));
                if(size2 < 0){
                    printf("Erro ao receber do pipe\n");
                    exit(EXIT_FAILURE);
                }

            }

            if(camp->isProva_running == 1 && numEquipas == 4){

                int size3 = read(servidor_fd, &aux, sizeof(aux)); // recebe a equipa
                if(size3 < 0){
                    printf("Erro ao receber do pipe\n");
                    exit(EXIT_FAILURE);
                }

                enviaMensagemDeComeco(camp);
                //strcpy(aux.mvp_nome, camp->equipas[j].mvp_equipa_nome);

                if(camp->eliminatoria == 2){

                    if(strcmp(aux.msg, "Venci") == 0){
                        aux.eliminatoria = 1;
                    }

                    if(strcmp(aux.msg, "Perdi") == 0){
                        aux.eliminatoria = 0;
                    }

                }

                if(camp->eliminatoria == 1){
                    if(strcmp(aux.msg, "Venci") == 0){
                        aux.eliminatoria = 10;
                    }

                    if(strcmp(aux.msg, "Perdi") == 0){
                        aux.eliminatoria = 0;
                    }

                    camp = verificaMVP(camp);

                    enviaMensagemDeFinal(camp);
                    camp->isProva_running = 0;
                    resetaEquipas(); //funcao que resetaEquipas (nao implementado)
                    
                }

            }


            
        }
    }


    return 0;
}