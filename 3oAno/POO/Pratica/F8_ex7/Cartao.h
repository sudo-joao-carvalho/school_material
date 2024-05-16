s//
// Created by João Carvalho on 13/12/2023.
//

#ifndef F8_EX7_CARTAO_H
#define F8_EX7_CARTAO_H

#include <vector>

using namespace std;

class Cartao {

public:
    Cartao(const int& numero, Tarifario* t);

    bool autorizaChamada();

    void registaChamada(const int& segundos);
    void carregamento(const float& quantia);

private:
    float saldo;
    Tarifario* tarifario;
    int numero;
    vector<int> registoChamadas;

};


#endif //F8_EX7_CARTAO_H
