#include <iostream>
#include "Retangulo.h"

int main() {

    Retangulo r1(Ponto (1, 2), 2 , 3);

    cout << "Area: " << r1.calculaArea() << endl;
    cout << r1.obtemPontos() << endl;
    return 0;
}
