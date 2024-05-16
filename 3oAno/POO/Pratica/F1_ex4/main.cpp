#include <iostream>

using namespace std;

int main() {

    string word;
    string reversed;
    string goNext;

    while(true){
        cout << "Escreve uma palavra:" << endl;

        getline(cin, reversed);
        word = reversed;
        reverse(word.begin(), word.end());

        if(word == reversed){
            cout << "Palindromo" << endl;
        }

        if(reversed == "fim")
            break;

        cout << "Carregue enter para prosseguir" << endl;
        getline(cin, goNext);

        while(!goNext.empty()){ // como o \n nao fica no getline entao eu nao posso fazer a comparaçao com o == e assim tenho que ver se a string fica vazia porque como o \n nao fica nastrin se carregar so no enter a string fica vazia
            cout << "Carregue enter para prosseguir" << endl;
            getline(cin, goNext);
        }

    }

    return 0;
}
