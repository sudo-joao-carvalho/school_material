//
// Created by João Carvalho on 28/10/2022.
//

#include "conta.h"

Conta::Conta(Pessoa *pessoa):pess(pessoa), saldo(0){}

void Conta::aumentaSaldo(int valor) {
    if(valor < 0)
        valor *= -1;

    saldo += valor;
}

void Conta::diminuiSaldo(int valor) {

    if(saldo > valor)
        saldo -= valor;

    if(saldo < 0)
        saldo = 0;
}

int Conta::obtemSaldo(int saldo) const{
    return saldo;
}

const Pessoa& Conta::obtemTitular() const{ //o const & permite que a pessoa nao seja mudada depoiis
    return *pess;
}

string Conta::obtemConta() const {
    ostringstream oss;

    oss << "Nome: " << pess->getNome() << "\nSaldo: " << saldo << endl;

    return oss.str();
}