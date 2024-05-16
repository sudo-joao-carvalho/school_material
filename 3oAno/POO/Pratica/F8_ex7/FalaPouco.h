//
// Created by João Carvalho on 13/12/2023.
//

#ifndef F8_EX7_FALAPOUCO_H
#define F8_EX7_FALAPOUCO_H


#include "Tarifario.h"

using namespace std;

class FalaPouco: public Tarifario {

public:
    FalaPouco(){}

    bool autorizaChamada(const float& saldo) override;
    float carregamento(const float& quantia) override;
    float calculaCusto(const int& duracao) override;

    string obtemNome() override;
private:


};


#endif //F8_EX7_FALAPOUCO_H
