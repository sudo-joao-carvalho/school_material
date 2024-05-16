//
// Created by João Carvalho on 29/11/2023.
//

#include "Tubarao.h"
#include <sstream>

Tubarao::Tubarao(const string& cor):Peixe(cor, 15){}

void Tubarao::alimenta(const int &quantidade) { //aqui a quantidade é o peso do outro peixe

    if(this->peso > 20){
        peso--;
    }

    if(aquario.temPeixes())
        if(peso < 20)
            peso += quantidade;
    else
        if(peso < 20)
            peso -= 2;

    if(peso < 5)
        aquario.eliminaPeixe(this->getID());
}

string Tubarao::getEspecie() const {
    return "Tubarao";
}

string Tubarao::getCor() const {
    return "Cinzento";
}

string Tubarao::getPeixeAsString() const {
    ostringstream oss;

    oss << "Especie: " << getEspecie() << endl
        << "Cor: " << getCor() << endl
        << "Peso: " << this->peso << endl;

    return oss.str();

}

Peixe* Tubarao::duplica() {return new Tubarao(*this);}