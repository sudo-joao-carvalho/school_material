//
// Created by João Carvalho on 06/12/2023.
//

#include "Apressado.h"

int Apressado::calculaPagamento() {

    int valor = 0;

    for(int i = 0; i < getTamanho(); ++i){
        int tempo = getTempos()[i];

        if(tempo > 0 && tempo <= 10) valor += 10;
        if(tempo >= 11 && tempo <= 20) valor += 15;
        if(tempo >= 21) valor += 25;
    }

    apagaTreinos();

    return valor;
}