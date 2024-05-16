//
// Created by João Carvalho on 27/10/2022.
//

#ifndef FICHA3_4_DESENHO_H
#define FICHA3_4_DESENHO_H

#include <iostream>
#include <string>
#include <sstream>
#include <vector>

#include "../ficha3_ex3/Retangulo.h"

using namespace std;

class Desenho{
private:
    string nome;
    vector<Retangulo> rect;

public:
    Desenho(const string& nome);
    bool adicionaRetangulo(const Retangulo& ret);
    void eliminaRetangulo(const int& area);
    string obtemRetanguloCujoPonto(const Ponto& pt) const;
    int somaArea() const;
    ~Desenho();

};

#endif //FICHA3_4_DESENHO_H
