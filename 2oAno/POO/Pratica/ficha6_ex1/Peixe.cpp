//
// Created by João Carvalho on 09/12/2022.
//

#include "Peixe.h"

int Peixe::generatorSN = 0;

Peixe::Peixe(const string& especie, const string& cor):especie(especie), cor(cor), peso(10), numSerie(++generatorSN), podeComer(true), isMorto(0){ //morto = 1

}

Peixe::~Peixe() {

}

void Peixe::alimenta(const int &pesoALimento) {
    peso += pesoALimento;

    if(peso > 50){

    }
}

bool Peixe::estaMorto() {
    return isMorto == 1; // isto é o mm que fazer um if isMorto == 0 esta vivo else isMorto == 1 esta morto
}

string Peixe::getEspecie() const {
    return especie;
}
