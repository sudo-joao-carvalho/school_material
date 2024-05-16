//
// Created by João Carvalho on 02/12/2022.
//

#ifndef FICHA5_EX5_PESSOA_H
#define FICHA5_EX5_PESSOA_H

#include <iostream>
#include <sstream>
#include <string.h>
#include <vector>

using namespace std;

class Pessoa {
public:
    Pessoa(const string& nome);

    string getNome() const{
        return nome;
    }
private:
    string nome;
};


#endif //FICHA5_EX5_PESSOA_H
