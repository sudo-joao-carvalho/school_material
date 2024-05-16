//
// Created by João Carvalho on 25/10/2023.
//

#ifndef F4_EX8_PESSOA_H
#define F4_EX8_PESSOA_H

#include <string>
#include <iostream>
#include <sstream>

using namespace std;

class Pessoa {
    int bi,nif;
    /*std::*/ string nome;

public:
    Pessoa(string nome,int bi,int nif):nome(nome),bi(bi),nif(nif){}
    string getNome();
    int getBi() ;
    int getNif();
    void setNome(const string& novoNome);
    string getDescricao();
};



#endif //F4_EX8_PESSOA_H
