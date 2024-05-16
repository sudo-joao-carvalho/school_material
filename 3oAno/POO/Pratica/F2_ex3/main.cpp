#include <iostream>

using namespace std;

/*  a) int multiplica(){
    return 1;
}

int multiplica(const int& num1){
    return num1;
}

int multiplica(const int& num1, const int& num2){
    return num1 * num2;
}

int multiplica(const int& num1, const int& num2, const int& num3){
    return num1 * num2 * num3;;
}*/

int multiplica(const int& num1 = 1, const int& num2 = 1, const int& num3 = 1){ // b)
    return num1 * num2 * num3;
}

int main(){
    cout << "\n" << multiplica() << "\n" << multiplica(5);
    cout << endl << multiplica(2,3) << endl << multiplica(2,3,4);

    return 0;
}
