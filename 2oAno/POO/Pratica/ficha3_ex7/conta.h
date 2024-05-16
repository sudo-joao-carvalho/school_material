//
// Created by João Carvalho on 28/10/2022.
//

#ifndef FICHA3_EX7_CONTA_H
#define FICHA3_EX7_CONTA_H

#include "pessoa.h"

class Conta{
public:
    Conta(Pessoa* pessoa);
    void aumentaSaldo(int valor);
    void diminuiSaldo(int valor);
    int obtemSaldo(int saldo) const;
    const Pessoa& obtemTitular() const;
    string obtemConta() const;
private:
    int saldo;
    Pessoa* pess;
};

#endif //FICHA3_EX7_CONTA_H
