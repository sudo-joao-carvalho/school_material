//
// Created by João Carvalho on 24/11/2022.
//

#ifndef FICHA5_EX1_MSG_H
#define FICHA5_EX1_MSG_H

#include <iostream>

using namespace std;

class MSG {
public:
    MSG(const char * p) {
        cout << "ola" << p << "\n";
    } ~MSG() {
        cout << "Adeus\n";
    }
};

#endif //FICHA5_EX1_MSG_H
