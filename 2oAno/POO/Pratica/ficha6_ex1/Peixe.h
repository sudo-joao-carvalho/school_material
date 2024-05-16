//
// Created by João Carvalho on 09/12/2022.
//

#ifndef FICHA6_EX1_PEIXE_H
#define FICHA6_EX1_PEIXE_H

#include <iostream>
#include <sstream>
#include <string>
#include <vector>
#include <cstdlib>

using namespace std;

class Aquario;

class Peixe{
public:
    Peixe(const string& especie, const string& cor = "cinzento");
    ~Peixe();
    void alimenta(const int& pesoALimento);
    string getAsString() const;
    bool estaMorto();

    virtual string getEspecie() const;
private:
    string especie;
    string cor;
    int peso;
    int numSerie;
    bool podeComer;
    int isMorto;

    static int generatorSN; //static int pq é comum a todos os peixes
};

class Sardinha:public Peixe{
public:
    string getEspecie() const override {
        return "sardinha";
    }
private:
};
#endif //FICHA6_EX1_PEIXE_H
