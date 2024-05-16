//
// Created by João Carvalho on 29/11/2023.
//

#ifndef F8_EX4_AQUARIO_H
#define F8_EX4_AQUARIO_H

#include "Peixe.h"

using namespace std;

class Aquario {
public:
    Aquario() = default;

    void adicionaPeixe(Peixe* novo);
    void eliminaPeixeById(const int& id);

    const Peixe* getPeixe() const;

    void alimentaPeixe(const int& quantidade);

    string getAquarioAsString() const;
private:
    vector<Peixe*> peixes;
};


#endif //F8_EX4_AQUARIO_H
