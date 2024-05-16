//
// Created by João Carvalho on 21/10/2023.
//

#ifndef F4_EX3_RETANGULO_H
#define F4_EX3_RETANGULO_H

#include <string>
#include "Ponto.h"

using namespace std;

class Retangulo {

    Ponto upLeftCorner;
    //Ponto* upLeftCorner;
    int height; //y
    int width; //x

public:

    Retangulo(const Ponto& upLeftCorner, int height, int width);
    ~Retangulo();
    //Retangulo(Ponto* upLeftCorner, int height, int width);

    //GETTERS & SETTERS

    Ponto getPonto() const;
    //Ponto* getPonto() const;
    string getPontoAsString() const;
    int getHeight() const;
    int getWidth() const;

    void setPontoByCords(int x, int y);
    void setPonto(const Ponto& ponto);
    //void setPonto(Ponto* ponto);
    void setHeight(int newHeight);
    void setWidth(int newWidth);

    int getArea() const;

    string getRetanguloAsString() const;
};


#endif //F4_EX3_RETANGULO_H
