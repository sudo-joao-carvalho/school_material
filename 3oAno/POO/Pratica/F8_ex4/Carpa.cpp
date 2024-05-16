//
// Created by João Carvalho on 29/11/2023.
//

#include "Carpa.h"
#include <sstream>

Carpa::Carpa(const string& cor): Peixe(cor, 5){}

void Carpa::alimenta(const int &quantidade) {

    Peixe::alimenta(quantidade);
    if(getPeso() >= 50){
        this->setPeso(20);
        //return new Carpa("laranja");
    }

    //return nullptr;
    /*peso += quantidade;

    if(this->peso >= 50){
        this->peso = 20;
        return
    }*/
}

string Carpa::getEspecie() const {
    return "Carpa";
}

string Carpa::getPeixeAsString() const {
    ostringstream oss;

    oss << "Especie: " << getEspecie() << endl
        << "Cor: " << getCor() << endl
        << "Peso: " << getPeso() << endl;

    return oss.str();

}

//falta fazer construtor por copia e redefinir operador= em cada classe derivada
Peixe* Carpa::duplica() {return new Carpa(*this);}