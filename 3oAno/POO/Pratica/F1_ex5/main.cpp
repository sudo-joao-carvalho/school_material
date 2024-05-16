#include <iostream>

using namespace std;

int main() {

    /*string number;
    string goNext;


    while(true) {
        cout << "Escreva um numero de 1 a 10:" << endl;
        getline(cin, number);

        if(number == "fim")
            break;

        if(isdigit(number[0])){
            int num = stoi(number);
            if(num == 1)
                cout << "um" << endl;
            if(num == 2)
                cout << "dois" << endl;
            if(num == 3)
                cout << "tres" << endl;
            if(num == 4)
                cout << "quatro" << endl;
            if(num == 5)
                cout << "cinco" << endl;
            if(num == 6)
                cout << "seis" << endl;
            if(num == 7)
                cout << "sete" << endl;
            if(num == 8)
                cout << "oito" << endl;
            if(num == 9)
                cout << "nove" << endl;
            if(num == 10)
                cout << "dez" << endl;

        }else{
            if(number == "um")
                cout << 1 << endl;
            if(number == "dois")
                cout << 2 << endl;
            if(number == "tres")
                cout << 3 << endl;
            if(number == "quatro")
                cout << 4 << endl;
            if(number == "cinco")
                cout << 5 << endl;
            if(number == "seis")
                cout << 6 << endl;
            if(number == "sete")
                cout << 7 << endl;
            if(number == "oito")
                cout << 8 << endl;
            if(number == "nove")
                cout << 9 << endl;
            if(number == "dez")
                cout << 10 << endl;
        }

        cout << "Carregue enter para prosseguir" << endl;
        getline(cin, goNext);

        while(!goNext.empty()){ // como o \n nao fica no getline entao eu nao posso fazer a comparaçao com o == e assim tenho que ver se a string fica vazia porque como o \n nao fica nastrin se carregar so no enter a string fica vazia
            cout << "Carregue enter para prosseguir" << endl;
            getline(cin, goNext);
        }
    }*/

    int num;
    string numS;
    string goNext;

    while(true) {
        cout << "Escreva um numero de 1 a 10:" << endl;
        //cin >> num;

        //if(cin.fail()){
        if(cin >> num){
            //testar se o valor esta entre 1 e 10
            if(num == 1)
                cout << "um" << endl;
            if(num == 2)
                cout << "dois" << endl;
            if(num == 3)
                cout << "tres" << endl;
            if(num == 4)
                cout << "quatro" << endl;
            if(num == 5)
                cout << "cinco" << endl;
            if(num == 6)
                cout << "seis" << endl;
            if(num == 7)
                cout << "sete" << endl;
            if(num == 8)
                cout << "oito" << endl;
            if(num == 9)
                cout << "nove" << endl;
            if(num == 10)
                cout << "dez" << endl;

        }else{

            cin.clear();
            cin >> numS;
            cin.ignore(1000, '\n');
            //string numS= to_string(num);
            if(numS == "fim")
                break;

            if(numS == "um")
                cout << 1 << endl;
            if(numS == "dois")
                cout << 2 << endl;
            if(numS == "tres")
                cout << 3 << endl;
            if(numS == "quatro")
                cout << 4 << endl;
            if(numS == "cinco")
                cout << 5 << endl;
            if(numS == "seis")
                cout << 6 << endl;
            if(numS == "sete")
                cout << 7 << endl;
            if(numS == "oito")
                cout << 8 << endl;
            if(numS == "nove")
                cout << 9 << endl;
            if(numS == "dez")
                cout << 10 << endl;

        }

        cout << "Carregue enter para prosseguir" << endl;
        getline(cin, goNext);

        while(!goNext.empty()){ // como o \n nao fica no getline entao eu nao posso fazer a comparaçao com o == e assim tenho que ver se a string fica vazia porque como o \n nao fica nastrin se carregar so no enter a string fica vazia
            cout << "Carregue enter para prosseguir" << endl;
            getline(cin, goNext);
        }
    }

    return 0;
}
