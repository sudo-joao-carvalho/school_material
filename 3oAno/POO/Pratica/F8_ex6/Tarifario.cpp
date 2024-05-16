//
// Created by João Carvalho on 06/12/2023.
//

#include "Tarifario.h"

Tarifario::Tarifario():tamanho(0), capacidade(10) {
    this->duracaoTreinos = new int(capacidade);
}

int* Tarifario::getTempos() const{
    return this->duracaoTreinos;
}

int Tarifario::getTamanho() const {return tamanho;}

/*void Tarifario::setTempo(const int& posicao, const int& valor){
    duracaoTreinos[posicao] = valor;
}*/

void Tarifario::apagaTreinos() {
    for(int i = 0; i < tamanho; i++)
        duracaoTreinos[i] = 0;
}
