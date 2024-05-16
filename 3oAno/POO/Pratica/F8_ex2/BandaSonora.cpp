//
// Created by João Carvalho on 29/11/2023.
//

#include "BandaSonora.h"
#include <sstream>
#include <iostream>

BandaSonora::BandaSonora(const string& titulo, const string &nomeFilme):CD(titulo), nomeFilme(nomeFilme) {}

string BandaSonora::obtemInfo() const {
    ostringstream oss;

    oss << CD::obtemInfo() << endl
        << "Nome Filme: " << nomeFilme << endl;

    return oss.str();
}

void BandaSonora::play() {
    cout << "Titulo: " << CD::obtemInfo()
        << "Nome FIlme: " << nomeFilme << endl
        << "Ja nao se fazem filmes como dantes" << endl;
}
