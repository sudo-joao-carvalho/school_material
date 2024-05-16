//
// Created by João Carvalho on 09/12/2022.
//

#ifndef FICHA6_EX1_AQUARIO_H
#define FICHA6_EX1_AQUARIO_H

#include <iostream>
#include <sstream>
#include <string.h>
#include <vector>
#include "Peixe.h"

using namespace std;

class Aquario{
public:
    Aquario() = default;
    void eliminaMorto();
private:
    vector<Peixe*> p;
};
#endif //FICHA6_EX1_AQUARIO_H
