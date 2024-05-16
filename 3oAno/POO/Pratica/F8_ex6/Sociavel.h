//
// Created by João Carvalho on 06/12/2023.
//

#ifndef F8_EX6_SOCIAVEL_H
#define F8_EX6_SOCIAVEL_H

#include "Cliente.h"

using namespace std;

class Sociavel: public Cliente {
public:
    Sociavel(const string& nome, const int& BI, Tarifario* tarifario);

    void reageEntrada() override;
    void reageSaida() override;
private:

};


#endif //F8_EX6_SOCIAVEL_H
