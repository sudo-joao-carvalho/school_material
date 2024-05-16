//
// Created by João Carvalho on 06/12/2023.
//

#ifndef F8_EX6_CLIENTE_H
#define F8_EX6_CLIENTE_H

#include <string>
#include "Tarifario.h"

using namespace std;

class Cliente {
public:
    Cliente(const string& nome, const int& BI, Tarifario* tarifario);

    Cliente(const Cliente& c) = delete; //isto faz como que seja impossivel criar uma copia do cliente

    void iniciaTreino(const int& horaInicio);
    void terminaTreino(const int& horaFim);

    int paga();

    virtual void reageEntrada() = 0;
    virtual void reageSaida() = 0;
private:
    string nome;
    int BI;
    Tarifario* tarifario;

    int horaInicio;
};


#endif //F8_EX6_CLIENTE_H
