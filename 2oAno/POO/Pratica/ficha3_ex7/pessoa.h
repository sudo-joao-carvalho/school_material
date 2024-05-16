//
// Created by João Carvalho on 28/10/2022.
//

#ifndef FICHA3_EX7_PESSOA_H
#define FICHA3_EX7_PESSOA_H

#include <string>
#include <vector>
#include <iostream>
#include <sstream>


using namespace std;

class Pessoa {
public:
    Pessoa(string nome, int nBI, int nContribuinte);
    string obtemDados() const;
    string getNome() const;
    int getNBI() const;
    int getContribuinte() const;
private:
    string nome;
    int nBI;
    int nContribuinte;
};

#endif //FICHA3_EX7_PESSOA_H
