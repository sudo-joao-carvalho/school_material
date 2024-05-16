//
// Created by João Carvalho on 29/11/2023.
//

#include "ColecaoCD.h"

ColecaoCD::ColecaoCD(ColecaoCD& orig){
    *this = orig;
}

ColecaoCD ColecaoCD::operator=(ColecaoCD &outro) {

    if(this == &outro){
        return *this;
    }

    this->colecao = outro.colecao;

    return *this;

}

bool ColecaoCD::adicionaCD(CD* novoCD) {
    colecao.push_back(novoCD);
    return true;
}
