//
// Created by João Carvalho on 29/11/2023.
//

#ifndef F8_EX4_CARPA_H
#define F8_EX4_CARPA_H

#include "Peixe.h"

using namespace std;

class Carpa: public Peixe{
public:
    Carpa(const string& cor);

    void alimenta(const int& quantidade) override;

    string getEspecie() const override;

    string getPeixeAsString() const override;

    Peixe* duplica() override;

private:
};


#endif //F8_EX4_CARPA_H
