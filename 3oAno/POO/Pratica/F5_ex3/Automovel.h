//
// Created by João Carvalho on 08/11/2023.
//

#ifndef F5_EX3_AUTOMOVEL_H
#define F5_EX3_AUTOMOVEL_H

#include <string>

using namespace std;

class Automovel {

public:
    Automovel(const string& marca, const string& modelo, const string& matricula);
    Automovel(Automovel& orig);

    Automovel operator=(Automovel outro);

private:
    static int contador;
    int id;

    string marca;
    string modelo;
    string matricula;

};


#endif //F5_EX3_AUTOMOVEL_H
