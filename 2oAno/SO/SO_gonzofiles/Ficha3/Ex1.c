#include <stdio.h>
#define MAX 10

int main(){
    char arr[MAX];
    printf("\nInsira chars: \n");
    scanf("%s", arr);
    printf("\n%s\n", arr);
    fflush(stdout);
    return 0;
}