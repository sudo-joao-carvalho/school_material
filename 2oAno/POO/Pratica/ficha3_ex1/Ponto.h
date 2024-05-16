//
// Created by João Carvalho on 21/10/2022.
//

#ifndef FICHA3_EX1_PONTO_H
#define FICHA3_EX1_PONTO_H

#include <iostream>
#include <string>
#include <vector>
#include <sstream>
#include <cmath>

using namespace std;

class Ponto{
public:
    Ponto(const int& x = 0, const int& y = 0);
    //Ponto(const int& x, const int& y);
    void setCordX(const int& x);
    void setCordY(const int& y);
    int getCordX() const;
    int getCordY() const;
    int area(){return x * y;} const;
    /*int &coordenadaX(){}
     * caso tivesse ponto p(1,2)
     * se depois fizesse p.coordenadaX() = 10 o valor deixaria de ser 1 e passava a ser 10 pq o return da funcao é uma referencia logo estaria a modificar diretamente a variavel*/

    double distancia(Ponto* outroPonto);
    string getAsString() const;
private:
    int x, y;
};

#endif //FICHA3_EX1_PONTO_H
