//
// Created by João Carvalho on 25/11/2022.
//

#ifndef FICHA5_EX2_ABC_H
#define FICHA5_EX2_ABC_H

#include <iostream>

using namespace std;

class ABC {
    char * p;
public:
    ABC(char * s) {
        p = new char[strlen(s)+1];
        strcpy(p,s);
    }
};

#endif //FICHA5_EX2_ABC_H
