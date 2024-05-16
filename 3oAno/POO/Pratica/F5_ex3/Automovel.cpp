//
// Created by João Carvalho on 08/11/2023.
//

#include "Automovel.h"

int Automovel::contador = 0;

Automovel::Automovel(const string& marca, const string& modelo, const string& matricula):marca(marca), modelo(modelo), matricula(matricula), id(contador++) {
}

Automovel::Automovel(Automovel &orig) {
    /*this->marca = orig.marca;
    this->matricula = orig.matricula;*/
    *this = orig;
}

Automovel Automovel::operator=(Automovel outro) {

    /*if(*this == outro){
        return *this;
    }*/

    //isto aqui e ver se estao na mesma posicao de memoria
    if(this == &outro){ //se for memoria dinamica temos que ver se tem a mesma referencia, ou seja estejam na mesma zona de memoria por isso podiamos redefenir o operador == para verificar isso
        return *this;
    }

    this->marca = outro.marca;
    this->modelo = outro.modelo;

    return *this;
}

