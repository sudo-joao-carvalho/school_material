//
// Created by João Carvalho on 21/10/2022.
//

#include "Retangulo.h"

Retangulo::Retangulo(Ponto ponto, int largura, int altura): largura(largura > 0? largura : -1), altura(altura > 0? altura : -1){
    Ponto p1(ponto.getPontoX(), ponto.getPontoY());
    cse = p1;
    //Ponto p2( ponto.getPontoX() + largura, ponto.getPontoY());  isto pode ser feito aqui mas estaria a usar memoria desnecessaria pois posso calcular estas coordenadas em qualquer parte do retangulo
    //Ponto p3(ponto.getPontoX(), ponto.getPontoY() + altura);
    //Ponto p4(ponto.getPontoX()+ largura, ponto.getPontoY() + altura);

    //recP.push_back(p1);
    //recP.push_back(p2);
    //recP.push_back(p3);
    //recP.push_back(p4);
}

int Retangulo::calculaArea() const{
    return largura * altura;
}

//Ponto Retangulo::getPonto(Ponto& ponto) const {

    //for(vector<Ponto>::iterator it = recP.begin(); it != recP.end(); ++it){
    //return Ponto(ponto.getPontoX() , ponto.getPontoY());
    //}
//}

int Retangulo::getPontoX() const {
    return cse.getPontoX();
}

int Retangulo::getPontoY() const {
    return cse.getPontoY();
}

int Retangulo::getLarg() const{
    return largura;
}

int Retangulo::getAlt() const{
    return altura;
}

string Retangulo::obtemPontos() const{
    ostringstream oss;

    Ponto ponto;
    for(unsigned int i = 0; i < recP.size(); i++){
        ponto = recP[i];
        oss << "Ponto: " << endl << "x: " << ponto.getPontoX() << endl << "y: " << ponto.getPontoY() << endl;
    }

    return oss.str();
}