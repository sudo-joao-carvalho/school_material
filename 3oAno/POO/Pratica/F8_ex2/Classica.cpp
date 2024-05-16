//
// Created by João Carvalho on 29/11/2023.
//

#include "Classica.h"
#include <sstream>

Classica::Classica(const string& titulo, const string &nomeMaestro, const string &nomeCompositor):CD(titulo), nomeMaestro(nomeMaestro), nomeCompositor(nomeCompositor) {}

string Classica::obtemInfo() const {
    ostringstream oss;

    oss << CD::obtemInfo() << endl
        << "Nome Maestro: " << nomeMaestro << endl
        << "Nome Compositor: " << nomeCompositor << endl;

    return oss.str();
}

void Classica::play() {
    cout << "A tocar musica boa" << endl;
}