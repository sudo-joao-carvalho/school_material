//
// Created by João Carvalho on 13/12/2023.
//

#include "Tagarela.h"

bool Tagarela::autorizaChamada(const float& saldo) {
    return saldo > -primeiroMinuto;
}

float Tagarela::carregamento(const float &quantia) {
    float novoSaldo = 0;

    if(quantia < 25) return novoSaldo;
}

float Tagarela::calculaCusto(const int& duracao) {
    int minuto = (duracao / 60) + (duracao % 60 > 0 ? 1: 0);

    return 0.5 + (minuto - 1) * minutosSeguintes;
}

string Tagarela::obtemNome() {
    return "Tagarela";
}
