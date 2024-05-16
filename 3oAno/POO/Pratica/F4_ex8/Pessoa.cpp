//
// Created by João Carvalho on 25/10/2023.
//

#include "Pessoa.h"

#include "Pessoa.h"


string Pessoa::getNome() {

    return nome;

}

int Pessoa::getBi() {

    return bi;
}

int Pessoa::getNif() {

    return nif;
}

void Pessoa::setNome(const string& novoNome) {

    nome=novoNome;

}

string Pessoa::getDescricao() {

    ostringstream oss;

    oss<< "Pessoa: "<< nome << " BI: " << bi << " NIF:  " << nif << endl;
    return oss.str();
}