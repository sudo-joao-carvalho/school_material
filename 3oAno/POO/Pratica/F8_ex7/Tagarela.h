//
// Created by João Carvalho on 13/12/2023.
//

#ifndef F8_EX7_TAGARELA_H
#define F8_EX7_TAGARELA_H

#include "Tarifario.h"
#include <string>

using namespace std;

class Tagarela: public Tarifario {
public:
    Tagarela(){}

    bool autorizaChamada(const float& saldo) override;
    float carregamento(const float& quantia) override;
    float calculaCusto(const int& duracao) override;

    string obtemNome() override;

private:
    const float primeiroMinuto = 0.5f;
    const float minutosSeguintes = 0.02f;

};


#endif //F8_EX7_TAGARELA_H
