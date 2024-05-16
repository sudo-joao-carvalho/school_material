#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <fcntl.h>
#include <string.h>
#include <sys/select.h>
#include <pthread.h>
#include <signal.h>
//alinea a)
/*typedef struct main
{
    char tipo;
    int stop;
} TDADOS;


void imprimir(char sinal, int tempo){
    for(int i = 0; i < tempo; i++){
        printf("%c", sinal);
        fflush(stdout);
        sleep(1);
    }
}

void* tarefa(void* dados){
    TDADOS *pdados = (TDADOS*) dados;

    while(pdados->stop){
        srand(time(0));
        int t = rand() % 1 - 5;
        imprimir('.', t); //imprimir os pontos
        //inicio de uma zona critica pois n quer que ninguem aceda a esta parte de memoria quando esta a imprimir
        imprimir(pdados->tipo, 3);
        //fim de uma zona critica
    }

    pthread_exit(NULL);
}

int main(int argc, char **argv){

    char buffer[100];
    pthread_t t[2];
    TDADOS valores[2];
    valores[0].stop = 1;
    valores[0].tipo = 'A';

    valores[1].stop = 1;
    valores[1].tipo = 'B';

    if(pthread_create(&t[0], NULL, &tarefa, &valores[0]) != 0)
        return -1;

    if(pthread_create(&t[1], NULL, &tarefa, &valores[1]) != 0)
        return -1;

    while(strcmp(buffer, "sair") != 0)
        scanf(" %s", buffer);

    valores[0].stop = 0;
    valores[1].stop = 0;
    pthread_join(t[0], NULL);
    pthread_join(t[1], NULL);    

    return 0;
}*/

//alinea a)

//alinea b)
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <fcntl.h>
#include <string.h>
#include <sys/select.h>
#include <pthread.h>
#include <signal.h>

typedef struct{
 char tipo;  
 int stop;
 pthread_mutex_t *m;
} TDADOS;

void imprimir (char sinal,int printNumber){
        for (int i=0;i<printNumber;i++){
            printf("%c",sinal);
            fflush(stdout);
            sleep(1);
        }
}

void *tarefa(void *dados){
    TDADOS *pdados = (TDADOS *) dados;
    while (pdados->stop){
        srand(time(0));  
        int t= rand() % (1 - 5);
        imprimir('.',t); // imprimir os pontos
        //inicio da zona critica
        pthread_mutex_lock(pdados->m);
            imprimir(pdados->tipo,3); //imprimir as letras A B
        pthread_mutex_unlock(pdados->m);
        //fim da zona critica

    }
    pthread_exit(NULL);
}


int main (){
    char buffer[100];
    pthread_t t[2];
    TDADOS valores[2];
    pthread_mutex_t mutex;
    pthread_mutex_init(&mutex, NULL);
    valores[0].stop = 1;
    valores[0].tipo = 'A';
    valores[0].m = &mutex;
    valores[1].stop = 1;
    valores[1].tipo = 'B';
    valores[1].m = &mutex;
    if(pthread_create(&t[0],NULL,&tarefa,&valores[0])!=0)
        return -1;
     if(pthread_create(&t[1],NULL,&tarefa,&valores[1])!=0)
        return -1;
    while (strcmp(buffer,"sair")!=0)
        scanf("%s",buffer);
    valores[0].stop = 0;
    valores[1].stop = 0;
    pthread_join(t[0], NULL);
    pthread_join(t[1], NULL);
    pthread_mutex_destroy(&mutex);
    //pthread_kill(t[0], SIGUSR1);
    return 0;
}