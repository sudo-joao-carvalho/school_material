//
// Created by João Carvalho on 20/10/2022.
//

#include "televisao.h"
#include <iostream>
#include <string>
#include <sstream>


using namespace std;

bool Televisao::liga(){
    return isLigada = true;
}

bool Televisao::desliga() {
    return isLigada = false;
}

string Televisao::estadoTV() const{
    if(isLigada){
        return "Ligada";
    }else
        return "Desligada";
}

int Televisao::mudaCanal(const int& nrCanal){
    if(isLigada && nCanais <= canais.size() && nrCanal > 0){
        canal = nrCanal;
    }
    return canal;
}

void Televisao::aumentaVolume() {
    if(isLigada && volume < 10 && volume >= 0) {
        volume++;
    }
}

void Televisao::baixaVolume(){
    if(isLigada && volume > 0 && volume <= 10) {
        volume--;
    }
}

string Televisao::mostraCanais() const{
    ostringstream oss;

    /*for(int i = 0; i < canais.size(); i++)*/
    for(const string& canal: canais){
        oss << canal << endl;
    }

    return oss.str();
}

string Televisao::obtemEstado() const{
    ostringstream oss;

    oss << "Estado: " << estadoTV() << endl << "Volume: " << volume << endl << "Canal: " << canais.at(canal) << endl;

    return oss.str();
}

void Televisao::apagarCanal(string nomeCanal){
    for(vector<string>::iterator it = canais.begin(); it != canais.end(); ++it){
        if(*it == nomeCanal){
            canais.erase(it); //o erase da return a posicao seguinte do vector
            return;
        }
    }
}

Televisao::Televisao(initializer_list<string> listaCanais):isLigada(true), volume(0), canal(0), canais(listaCanais)/*lista de inicializaçao*/{}






