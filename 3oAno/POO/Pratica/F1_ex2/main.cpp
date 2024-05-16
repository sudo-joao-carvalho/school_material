#include <iostream>

using namespace std;

int main() {

    // 2. a)
    /*char nome[100];
    int idade;

    printf("Qual o seu nome?");
    scanf(" %s", nome);

    fflush(stdin);

    printf("Qual a sua idade?");
    scanf(" %d", &idade);

    printf("Nome: %s\nIdade: %d", nome, idade);*/

    // 2. b)
    /*string nome;
    int idade;

    cout << "Qual o seu nome?" << endl;
    cin >> nome;

    cout << "Qual a sua idade?" << endl;
    cin >> idade;

    cout << "Nome: " << nome << endl << "Idade: " << idade << endl;*/

    // 2. c) i.

    /*string nome;
    string idade;

    cout << "Qual o seu nome?" << endl;
    cin >> nome;

    cout << "Qual a sua idade?" << endl;
    cin >> idade;

    stoi(idade);
    cout << "Nome: " << nome << endl << "Idade: " << idade << endl;*/

    // 2. c) ii.

    /*string nome;
    string idade;

    cout << "Qual o seu nome?" << endl;
    getline(cin, nome);

    cout << "Qual a sua idade?" << endl;
    getline(cin, idade);

    stoi(idade);
    cout << "Nome: " << nome << endl << "Idade: " << idade << endl;*/

    // 2. e) i)

    /*string nome;
    string idade;

    cout << "Qual o seu nome?" << endl;
    getline(cin, nome);

    cout << "Numero de caracteres [nome]: " << nome.length() << endl;

    for(int i = 0; i < nome.length(); i++){
        cout << nome[i] << endl;
    }

    for(char c: nome){
        cout << c << endl;
    }*/


    return 0;
}
