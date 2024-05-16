#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <string.h>

int main(int arc, char** argv){

    char array[100];

    printf("Insira port ou ing: ");
    scanf(" %s", array);

    setbuf(stdout, NULL);

    if(strcmp(array, "./port") == 0){
        execl("./port", "./port", NULL);
    }else if(strcmp(array, "./ing") == 0){
        execl("./ing", "./ing", NULL);
    }

    return 0;
}