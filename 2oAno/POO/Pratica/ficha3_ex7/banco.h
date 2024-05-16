//
// Created by João Carvalho on 28/10/2022.
//

#ifndef FICHA3_EX7_BANCO_H
#define FICHA3_EX7_BANCO_H

#include "conta.h"

using namespace std;

class Banco{
public:
    Banco(string nome);
    void addConta(Pessoa* p);
    string obtemInfoConta(int BI);
    void eliminaConta(int BI);
private:
    string nome;
    vector<Conta> contas;
};
#endif //FICHA3_EX7_BANCO_H
