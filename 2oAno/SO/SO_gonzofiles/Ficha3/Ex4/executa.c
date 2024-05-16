#include <stdio.h>
#include <string.h>
#include <unistd.h>

int main(){
    char arr[100];

    setbuf(stdout, NULL);

    
    printf("\n[&d] Insira uma cadeia de carateres:\n", getpid());
    scanf("%s", arr);

    
    if (strcmp(arr, "port") == 0){
        execl("./port", "./port", NULL, NULL);
    }

    else if (strcmp(arr, "ing") == 0){
        execl("./ing", "./ing", NULL, NULL);
    }

    printf("\nMissao cumprida!\n");

    return 0;
}