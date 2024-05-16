#include <iostream>

#include "Retangulo.h"

using namespace std;

int main() {

    //a)
    Ponto p1(1, 2);
    Retangulo a(p1, 1, 2);
    Retangulo b(p1, 2, 1);
    //Retangulo a(&p1, 1, 2);
    //Retangulo b(&p1, 2, 1);

    //b)
    p1.setX(4);
    p1.setY(5);
    a.setPonto(p1);
    //a.setPontoByCords(p1.getX(), p1.getY());

    //cout << a.getRetanguloAsString() << b.getRetanguloAsString() << endl;

}
