//
// Created by João Carvalho on 21/10/2022.
//

#ifndef FICHA3_EX3_RETANGULO_H
#define FICHA3_EX3_RETANGULO_H

#include <iostream>
#include <string>
#include <sstream>
#include <vector>

using namespace std;

class Ponto{
public:
    Ponto(int x = 0, int y = 0): x(x), y(y){};
    int getPontoX() const{return x;};
    int getPontoY() const{return y;};
private:
    int x, y;
};

class Retangulo{
public:
    Retangulo(Ponto ponto, int largura, int altura);
    Retangulo(int x, int y, int largura, int altura);
    int calculaArea() const;
    string obtemPontos() const;
    void setPontoX(int novaCordX);
    void setPontoY(int novaCordY);
    Ponto getPonto(Ponto& ponto) const;
    int getPontoX() const;
    int getPontoY() const;
    int getLarg() const;
    int getAlt() const;

private:
    Ponto cse; //cse-> canto superior esquerdo
    vector<Ponto> recP;
    int largura;
    int altura;

};


#endif //FICHA3_EX3_RETANGULO_H
