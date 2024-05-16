#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <unistd.h>

int main()
{
    int i;
    char resposta[50];
    int fd[2];
    pipe (fd);

    i = fork();

    if(i < 0)
        return -1;
    else if(i == 0){
        close(fd[0]); //fecha a leitura stdin pois vou escrever no pipe
        int size = write(fd[1], "ola", 10);
        printf("Enviei [%d]\n", size);
    }
    else{
        close(fd[1]); //fecha a escrita stdout pois vou ler do pipe
        int size2 = read(fd[0], resposta, sizeof(resposta));
        printf("\nEu recebi a palavra \'%s\' com \'%d\' de tamanho!\n", resposta, size2);
    }

    return 0;
}
