//
// Created by João Carvalho on 21/10/2023.
//

#include "Retangulo.h"
#include <iostream>

Retangulo::Retangulo(const Ponto& upLeftCorner, int height, int width): upLeftCorner(upLeftCorner), height(height), width(width){
    cout << "Retangulo com canto superior esquerdo em " << upLeftCorner.getAsString() << " construido..." << endl;
}

Retangulo::~Retangulo(){
    cout << "Retangulo com canto superior esquerdo em " << upLeftCorner.getAsString() << " destruido..." << endl;
}

/*Retangulo::Retangulo(Ponto* upLeftCorner, int height, int width): upLeftCorner(upLeftCorner), height(height), width(width){

}*/

//Getters
Ponto Retangulo::getPonto() const {return upLeftCorner;}
//Ponto* Retangulo::getPonto() const {return upLeftCorner;}

string Retangulo::getPontoAsString() const {return upLeftCorner.getAsString();}
//string Retangulo::getPontoAsString() const {return upLeftCorner->getAsString();}

int Retangulo::getHeight() const {return height;}

int Retangulo::getWidth() const {return width;}

int Retangulo::getArea() const {return height * width;}

string Retangulo::getRetanguloAsString() const {

    ostringstream oss;

    oss << "Canto Superior Esquerdo: " << upLeftCorner.getAsString()
        << "Altura: " << height << endl
        << "Largura: " << width << endl
        << "Area: " << getArea() << endl << endl;

    return oss.str();
}

//Setters
void Retangulo::setPontoByCords(int x, int y) {
    upLeftCorner.setX(x);
    upLeftCorner.setY(y);
}

void Retangulo::setPonto(const Ponto& ponto) {
    upLeftCorner = ponto;
}

/*void Retangulo::setPonto(Ponto* ponto) {
    upLeftCorner = ponto;
}*/

void Retangulo::setHeight(int newHeight) {
    height = newHeight;
}

void Retangulo::setWidth(int newWidth) {
    width = newWidth;
}
