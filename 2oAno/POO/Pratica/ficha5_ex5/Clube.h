//
// Created by João Carvalho on 02/12/2022.
//

#ifndef FICHA5_EX5_CLUBE_H
#define FICHA5_EX5_CLUBE_H

#include <iostream>
#include "Pessoa.h"
using namespace std;

class Clube {
    Pessoa * * socios;
    int tam;
public:
    Clube(int t) {
        // Isto é para fazer copy & paste
        // para o programa no computador.
        // Mas pode nem ser sequer preciso
        // -> Ver primeiro as perguntas.
// por questões de espaço assume-se
// que há memória e não dá erro, mas
// isto deve ser devidamente validado
//    (  if (socios != NULL) ...  )
        tam = t;
        socios = new Pessoa * [tam];
        for (unsigned int i=0; i<tam; i++)
            socios[i]= NULL;
    }
    ~Clube() { delete []socios; } //apenas faz o delete de array de socios pois é agregaçao e nao precisa de dar delete as pessoas
    void setMembroDoClube(Pessoa * p, int pos) {
        socios[pos] = p;
    }
};
// Notar que o obj. Pessoa é visto pelo Clube // mas o clube não toma posse desse objecto // (o clube é uma agregação de Pessoas)


#endif //FICHA5_EX5_CLUBE_H
