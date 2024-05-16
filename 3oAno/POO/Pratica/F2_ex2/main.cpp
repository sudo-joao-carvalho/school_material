#include <iostream>

using namespace std;

void imprime(const string& line){
    cout << line << endl;
}

void imprime(const string& line, const int& num){
    cout << line << " " << num << endl;
}

void imprime(const int& num, const string& line){
    cout << num << " " << line << endl;
}

int main(){
    imprime("programação orientada a objetos");
    imprime("horas por aula teórica ", 2);
    imprime(3, " horas em cada aula prática");
    return 0;
}