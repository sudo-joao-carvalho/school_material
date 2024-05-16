//
// Created by João Carvalho on 21/10/2022.
//

#include "Ponto.h"

Ponto::Ponto(const int &x, const int &y): x(x), y(y){}

void Ponto::setCordX(const int& x){
    this->x = x;
}

void Ponto::setCordY(const int& y){
    this->y = y;
}

int Ponto::getCordX() const {
    return x;
}

int Ponto::getCordY() const {
    return y;
}

double Ponto::distancia(Ponto* outroPonto){
    return sqrt(pow(this->x - outroPonto->x, 2) + pow(this->y - outroPonto->y, 2));
}

string Ponto::getAsString() const{
    ostringstream oss;

    oss << "(" << x << " / " << y << ")" << endl;

    return oss.str();
}