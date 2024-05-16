//
// Created by João Carvalho on 28/10/2022.
//

#include "pessoa.h"

Pessoa::Pessoa(string nome, int nBI, int nContribuinte) : nome(nome), nBI(nBI), nContribuinte(nContribuinte){}

string Pessoa::obtemDados() const {
    ostringstream oss;

    oss << " Pessoa -> " << nome << " Nr de BI:" << nBI
        << " Nr de Contribuinte: " << nContribuinte << endl;

    return oss.str();
}

string Pessoa::getNome() const {
    return nome;
}

int Pessoa::getContribuinte() const {
    return nContribuinte;
}

int Pessoa::getNBI() const {
    return nBI;
}