//
// Created by João Carvalho on 13/10/2023.
//

#include "Triangulo.h"

Triangulo::Triangulo(string nome, Ponto pontoA, Ponto pontoB, Ponto pontoC): nome(nome), pontoA(pontoA), pontoB(pontoB),
                                                                             pontoC(pontoC) {
}

string Triangulo::getNome() const {
    return nome;
}

Ponto Triangulo::getPontoA() const {
    return pontoA;
}

void Triangulo::setPontoA(Ponto newPontoA) {
    pontoA = newPontoA;
}

Ponto Triangulo::getPontoB() const {
    return pontoB;
}

void Triangulo::setPontoB(Ponto newPontoB) {
    pontoB = newPontoB;
}

Ponto Triangulo::getPontoC() const {
    return pontoC;
}

void Triangulo::setPontoC(Ponto newPontoC) {
    pontoC = newPontoC;
}

string Triangulo::getAsString() const {

    ostringstream oss;

    oss << "Triangulo " << nome << ": (" <<
        pontoA.getX() << " , " << pontoA.getY() << ") " <<
        "(" << pontoB.getX() << " , " << pontoB.getY() << ") " <<
        "(" << pontoC.getX() << " , " << pontoC.getY() << ") " << endl;

    return oss.str();
}
