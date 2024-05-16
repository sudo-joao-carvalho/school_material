//
// Created by João Carvalho on 20/10/2022.
//

#ifndef PRÁTICA_TELEVISAO_H
#define PRÁTICA_TELEVISAO_H

#include <iostream>
#include <string>
#include <sstream>
#include <vector>

using namespace std;

class Televisao{
public:
    bool liga();
    int mudaCanal(const int& nrCanal);
    bool desliga();
    void aumentaVolume();
    void baixaVolume();
    string estadoTV() const;
    string mostraCanais() const; //getAsString
    string obtemEstado() const; //getAsString
    void apagarCanal(string nome);
    Televisao(initializer_list<string> listaCanais); //vector é algo dinamico, a initializer_list tem o tamanho consoante as coisas que lhe passas
private:
    static const int nCanais = 5;
    vector<string> canais;
    int canal;
    int volume;
    bool isLigada;
};

#endif //PRÁTICA_TELEVISAO_H
