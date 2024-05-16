//
// Created by João Carvalho on 29/11/2023.
//

#ifndef F8_EX2_BANDASONORA_H
#define F8_EX2_BANDASONORA_H


#include "CD.h"

using namespace std;

class BandaSonora: public CD {
public:
    BandaSonora(const string& titulo, const string& nomeFilme);

    string obtemInfo() const override;

    void play() override;


private:
    string nomeFilme;
};


#endif //F8_EX2_BANDASONORA_H
