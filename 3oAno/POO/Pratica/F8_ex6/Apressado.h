//
// Created by João Carvalho on 06/12/2023.
//

#ifndef F8_EX6_APRESSADO_H
#define F8_EX6_APRESSADO_H

#include "Tarifario.h"

using namespace std;

class Apressado: public Tarifario { //ao fazer public Tarifario quer dizer que tudo o que é public e protected do Tarifario vem como public para a Classe derivada
public:
    Apressado() = default;
    int calculaPagamento() override;

private:

};


#endif //F8_EX6_APRESSADO_H
