//
// Created by João Carvalho on 25/10/2023.
//

#include "Vetor.h"

Vetor::Vetor(const double &db):x(db), y(db){}
Vetor::Vetor(const double &x, const double &y):x(x), y(y) {}

/*Vetor Vetor::operator+(Vetor b){

    Vetor aux;

    aux.x = this->x + b.x;
    aux.y = this->y + b.y;

    return aux;
}*/

Vetor Vetor::operator-(Vetor b){

    Vetor aux;

    aux.x = this->x - b.x;
    aux.y = this->y - b.y;

    return aux;
}

Vetor Vetor::operator+=(Vetor b){

    this->x += b.x;
    this->y = b.y;

    return *this;
}

bool Vetor::operator==(Vetor b) {

    if(this->x == b.x && this->y == b.y)
        return true;

    return false;
}

bool Vetor::operator!=(Vetor b) {
    //ou !(==)

    if(this->x != b.x || this->y != b.y)
        return true;

    return false;
}
