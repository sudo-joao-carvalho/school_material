//
// Created by João Carvalho on 29/11/2023.
//

#ifndef F8_EX2_CLASSICA_H
#define F8_EX2_CLASSICA_H


#include "CD.h"

using namespace std;

class Classica: public CD {
public:
    Classica(const string& titulo, const string& nomeMaestro, const string& nomeCompositor);

    string obtemInfo() const override;

    void play() override;

private:
    string nomeMaestro;
    string nomeCompositor;
};


#endif //F8_EX2_CLASSICA_H
