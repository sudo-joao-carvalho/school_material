//
// Created by João Carvalho on 12/10/2023.
//

#include "Ponto.h"

/*Ponto::Ponto(int x, int y){
    this->x = x;
    this->y = y;
}*/

Ponto::Ponto(int x, int y):x(x), y(y) {}

int Ponto::getX() const {
    return x;
}

void Ponto::setX(int x) {
    Ponto::x = x;
}

int Ponto::getY() const {
    return y;
}

void Ponto::setY(int y) {
    Ponto::y = y;
}

double Ponto::distanceBetweenPoints(int otherX, int otherY) {

    double ret;
    double firstHandOperator = (otherX - x);
    double secondHandOperator = (otherY - y);

    ret = sqrt((pow(firstHandOperator, 2) + pow(secondHandOperator, 2)));

    return ret;
}

double Ponto::distanceBetweenPoints(Ponto otherPoint) {

    double ret;
    double firstHandOperator = (otherPoint.x - x);
    double secondHandOperator = (otherPoint.y - y);

    ret = sqrt((pow(firstHandOperator, 2) + pow(secondHandOperator, 2)));

    return ret;
}

string Ponto::getAsString() const{

    ostringstream oss;

    oss << "(x / y): (" << x << " / " << y << ")" << endl;

    return oss.str();
}
