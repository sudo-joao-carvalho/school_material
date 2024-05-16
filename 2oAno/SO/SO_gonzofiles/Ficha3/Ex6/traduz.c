#include <stdio.h>
#include <sys/wait.h>
#include <unistd.h>

int main(){
    char letra;

    char palavra[25];

    do{
        printf("\nInsira uma letra:\n");

        scanf(" %c", &letra);

        if (letra == 'x'){
            return 0;
        }

        int id = fork();

        if (id == 0){
            if (letra == 'i'){
                printf("\nInsira a palavra a traduzir:\n");
                scanf("%s", palavra);
                execl("./ding", "./ding", palavra, NULL);
            }

            else if (letra == 'f'){
                printf("\nInsira a palavra a traduzir:\n");
                scanf("%s", palavra);
                execl("./dfran", "./dfran", palavra, NULL);
            }
        }

        else if (id > 0){
            wait(&id);
        }

    } while (letra != 'x');

    
}