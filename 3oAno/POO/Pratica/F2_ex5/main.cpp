#include <iostream>

using namespace std;

int& seleciona(int& num1, int& num2, const char& selector){

    if (selector == 'm') {
        return (num1 > num2) ? num2 : num1;
    } else if (selector == 'M') {
        return (num1 > num2) ? num1 : num2;
    } else if (selector == 'p') {
        return num1;
    } else if (selector == 'u') {
        return num2;
    }

    return num1;

}

int main(){
    int a = 5, b = 10;
    seleciona(a, b, 'm') = 0;
    cout << "a = " << a << " b = " << b; // aparece 0 10

    int c = 5, d = 10;
    seleciona(c, d, 'M') -= 3;
    cout << "c = " << c << " d = " << d; // aparece 5 7
}
