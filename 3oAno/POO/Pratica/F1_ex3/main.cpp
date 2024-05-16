#include <iostream>
#include <sstream>

using namespace std;

int main() {

    //3. a)
    string name;
    string firstName;

    cout << "Introduza o seu nome completo: " << endl;
    getline(cin, name);

    istringstream iss(name);

    iss >> firstName;
    if(firstName == "Fernando"){
        cout << "Conheço alguem com este nome" << endl;
    }

    string aux;

    cout << firstName << endl;
    while(iss >> aux){
        cout << aux << endl;
    }

    return 0;
}
