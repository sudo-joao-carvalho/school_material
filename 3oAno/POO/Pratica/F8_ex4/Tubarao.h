//
// Created by João Carvalho on 29/11/2023.
//

#ifndef F8_EX4_TUBARAO_H
#define F8_EX4_TUBARAO_H

#include "Peixe.h"

using namespace std;

class Tubarao: public Peixe {
public:
    Tubarao(const string& cor);

    void alimenta(const int& quantidade) override;

    string getEspecie() const override;

    string getPeixeAsString() const override;

    Peixe* duplica() override;
private:
    int peso;
};


#endif //F8_EX4_TUBARAO_H
