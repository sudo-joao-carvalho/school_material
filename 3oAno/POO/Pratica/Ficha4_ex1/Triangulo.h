//
// Created by João Carvalho on 13/10/2023.
//

#ifndef FICHA4_EX1_TRIANGULO_H
#define FICHA4_EX1_TRIANGULO_H

#include <string>
#include <sstream>
#include <cmath>
#include "Ponto.h"

using namespace std;

class Triangulo {

public:
    Triangulo(string nome, Ponto pontoA, Ponto pontoB, Ponto pontoC);

    string getNome() const;

    Ponto getPontoA() const;

    void setPontoA(Ponto newPontoA);

    Ponto getPontoB() const;

    void setPontoB(Ponto newPontoB);

    Ponto getPontoC() const;

    void setPontoC(Ponto newPontoC);

    string getAsString() const;

private:

    const string nome;
    Ponto pontoA;
    Ponto pontoB;
    Ponto pontoC;
};


#endif //FICHA4_EX1_TRIANGULO_H
