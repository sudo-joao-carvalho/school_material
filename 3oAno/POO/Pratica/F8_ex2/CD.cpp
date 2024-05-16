//
// Created by João Carvalho on 29/11/2023.
//

#include "CD.h"
#include <sstream>

CD::CD(const std::string &titulo):titulo(titulo) {}

string CD::obtemInfo() const{
    ostringstream oss;

    oss << "Titulo: " << titulo << endl
        << "Faixas: ";

    for(string faixa: faixas){
        oss << faixa << " ";
    }
    return oss.str();
}