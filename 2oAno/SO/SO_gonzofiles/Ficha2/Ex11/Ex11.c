#include <stdio.h>
#include <stdlib.h>

/*int main(int argc, char** argv, char** envp){
    int i = 1;
    char *p = envp[0];

    while (p != NULL){
        printf("%s\n", envp[i]);
        p = envp[++i];
    }
}*/

/*int main(int argc, char **argv){ // a usar a environ (variavel externa)
    extern char **environ;
    char *p = environ[0];
    int i = 0;
    while (p != NULL){
        printf("\nParametro [%d] - [%s]", i, environ[i]);
        p = environ[++i];
    }

    printf("\n");

    return 0;
}*/

int main(int argc, char **argv){ // com getenv()
    int i = 0;
    char s[] = "PATH";
    char *p;
    p = getenv(s);

    if (p != NULL){
        printf("\nVariavel: %s = %s\n", s,p);
    }

    else{
        printf("A variavel %s nao existe.", s);
    }

    return 0;
}