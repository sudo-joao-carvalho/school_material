#include <iostream>

using namespace std;

void troca(int& a, int& b){
    int aux = a;
    a = b;
    b = aux;
}

int main(){
    int a = 5, b = 10;
    troca(a, b);
    cout << "\na = " << a << "\nb = " << b;

    return 0;
} // deve aparecer a = 10 e b = 5
