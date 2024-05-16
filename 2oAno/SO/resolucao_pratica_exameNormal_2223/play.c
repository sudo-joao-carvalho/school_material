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

#define MAX_NOTES 100

typedef struct nota{
    char instrumento;
    char L;
    int N;
}nota;

int numAcordes = 0;
nota musica[MAX_NOTES];
nota notaUnica;
pid_t pid_inst[3];
pthread_mutex_t mutex;

void leave(){

    printf("\n\n%d notas/acordes", numAcordes);

    pthread_mutex_lock(&mutex);
        for(int i = 0; i < 3; i++){
            kill(pid_inst[i], SIGINT);
        }
    pthread_mutex_unlock(&mutex);
    exit(0);

}

void* tocaInst(void* i){

    int ins = (int*) i;

    pid_t pid;
    int fd[2];
    char buffer[2];
    char c;

    if(ins == 0){
        c = 'p';
    }else if(ins == 1){
        c = 'g';
    }else if(ins == 2){
        c = 'b';
    }

    if (pipe(fd) != 0)
    {
        fprintf(stderr, "Pipe Failed");
        return NULL;
    }

    int id = fork();

    if(id < 0){
        printf("Instrumento nao criado\n");
        return NULL;
    }else if(id == 0){
        close(1); //fecha o stdout do file descriptor
        dup(fd[1]); //duplica o stdout
        close(fd[0]); //fecha o antigo
        close(fd[1]); //fecha a outra ponta do pipe

        execl("./inst", "./inst", NULL);
        exit(0);
    }else if(id > 0){
        close(fd[1]);
        pid_inst[ins] = pid;

        while(1){
            int size = read(fd[0], buffer, sizeof(buffer));
            pthread_mutex_lock(&mutex);
                printf("%c", c);
                printf("%s", buffer);
                numAcordes++;
            pthread_mutex_unlock(&mutex);

        }
    }

    return NULL;
}

int main(int argc, char** argv){

    pthread_t inst[3];
    int tempo = atoi(getenv("TEMPO"));

    pthread_mutex_init(&mutex, NULL);

    signal(SIGALRM, &leave);
    alarm(tempo);

    for(int a = 0; a < 3; a++){
        if(pthread_create(&inst[a], NULL, &tocaInst, (void*) a))
            return -1;
    }

    for(int a = 0; a < 3; a++){
        pthread_join(&inst[a], NULL);
    }    

    return 0;
}