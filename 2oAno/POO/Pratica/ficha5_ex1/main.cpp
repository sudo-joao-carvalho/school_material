#include <iostream>
#include "MSG.h"

using namespace std;

//Alinea C
void func(MSG** c, MSG** d){ //tem que se passar o endereço e memoria desse ponteiro para depois podermos criar nesse sitio
    *c = new MSG(" Severino");
    *d = new MSG(" Pompeu");
}

int main() {

    //Alinea a
    //MSG *a = new MSG(" Manuel"), *b = new MSG(" Antonio");  se escrever nothrow faz com que a criacao do novo objeto retorne um null --> depois fazer a verificaçao como em C != nullptr
    MSG *c, *d;

    func(&c, &d);

    //delete a;
    //delete b;
    delete c;
    delete d;
    return 0;
}

//EXPLICACAO DO DUPLO PONTEIRO

/*

 Quando indicamos os objetos(ponteiros) na main sem os criar eles ficam a apontar para um endereço de memoria, e quando os criamos na funcao esses objetos eles sao
 criados noutro endereço de memoria, logo temos que usar duplo ponteiro e as referencias no chamada da funcao para garantirmos que os objetos que foram indicados na
 main apontem para os que foram criados na funcao

 */