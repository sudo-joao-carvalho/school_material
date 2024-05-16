//
// Created by João Carvalho on 06/12/2023.
//

#include "Sociavel.h"

Sociavel::Sociavel(const string& nome, const int& BI, Tarifario* tarifario): Cliente(nome, BI, tarifario) {}

void Sociavel::reageEntrada() {}

void Sociavel::reageSaida() {

    if(ginasio->getNumClientesNoGinasio() == 0){
        ginasio->sai();
    }
}