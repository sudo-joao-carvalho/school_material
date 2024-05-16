#include <stdio.h>
#include <stdlib.h>

//b -> usando envp
/*int main(int argc, char **argv, char** envp){

    for(int i = 0; envp[i] != NULL; i++){
        printf("%s", envp[i]);
    }

    return 0;
}*/

//b -> usando environ
/*int main(int argc, char **argv, char** envp){
    extern char** environ;

    for(int i = 0; environ[i] != NULL; i++){
        printf("%s", environ[i]);
    }

    return 0;
}*/

 int main(int argc, char **argv, char** envp){
    char s[] = "NOME";
    char* ptr;
    ptr = getenv(s);

    if (ptr != NULL){
        printf("\nVariavel %s = %s\n", s, ptr);
    }

    else{
        printf("A variavel %s nao existe.", s);
    }

    return 0;
} 