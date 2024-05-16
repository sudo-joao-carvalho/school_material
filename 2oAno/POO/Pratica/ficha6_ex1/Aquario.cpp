//
// Created by João Carvalho on 09/12/2022.
//

#include "Aquario.h"

void Aquario::eliminaMorto() {

    auto it = p.begin();

    while(it != p.end()){
        if((*it)->estaMorto()){
            delete *it;
            it = p.erase(it); //se o delete dos elementos de um vetor for feito no destrutor ele faz erase do vetor naturalmente
        }else it++;
    }
}
