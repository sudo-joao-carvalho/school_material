//
// Created by João Carvalho on 06/12/2023.
//

#include "Cliente.h"

Cliente::Cliente(const string& nome, const int& BI, Tarifario* tarifario):nome(nome), BI(BI), tarifario(tarifario){

}

void Cliente::terminaTreino(const int &horaFim) {
    int duracao = horaFim - this->horaInicio;

    tarifario->acrescentaTreino(duracao);
}

int Cliente::paga() {
    return tarifario->calculaPagamento();
}