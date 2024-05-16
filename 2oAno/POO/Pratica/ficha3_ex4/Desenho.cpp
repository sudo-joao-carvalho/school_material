//
// Created by João Carvalho on 27/10/2022.
//

#include "Desenho.h"

Desenho::Desenho(const string& nome):nome(nome){}

bool Desenho::adicionaRetangulo(const Retangulo& ret){
    for(unsigned int i = 0; i < rect.size(); i++){
        //if(rect[i].getPonto().getPontoX() == ret.getPonto().getPontoX()){
        //if()


    }


}

void Desenho::eliminaRetangulo(const int& area){
    vector<Retangulo>::iterator it;

    //cbegin() -> constante logo n se pode usar em iteradores em que vamos alterar cenas

    while(it != rect.end()){
        if(it->calculaArea() >= area){
            it = rect.erase(it);//como o erase devolve a proxima posiçao do vector temos que dizer que o it é igual a essa posicao
        }else
            it++;
    }
}

string Desenho::obtemRetangulo(const Ponto& pt) const {
    ostringstream oss;

    for(unsigned int i = 0; i < rect.size(); i++){
        if(rect[i].getPontoX() == pt.getPontoX() && rect[i].getPontoY() == pt.getPontoY()){
            oss << "X";
        }
    }
}



