//
// Created by João Carvalho on 29/11/2023.
//

#include "Peixe.h"
#include <sstream>

int Peixe::contador = 1000;

Peixe::Peixe(const string& cor, const int& peso):cor(cor), peso(peso), nSerie(contador++) {}

/*Peixe::Peixe(Peixe& orig){
    *this = orig;
}

Peixe Peixe::operator=(Peixe &outro) {

    if(this == &outro){
        return *this;
    }


}*/

int Peixe::getID() const {return nSerie;}
int Peixe::getPeso() const {return peso;}
string Peixe::getCor() const {return cor;}

void Peixe::setPeso(const int &quantidade) {
    peso += quantidade;
}

string Peixe::getPeixeAsString() const {
    ostringstream oss;

    oss << "ID: " << nSerie << endl;

    return oss.str();
}