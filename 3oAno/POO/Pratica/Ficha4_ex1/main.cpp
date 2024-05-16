#include <iostream>
#include <cstring>

#include "Ponto.h"
#include "Triangulo.h"

using namespace std;

int main() {

    /* EX 1
    Ponto a(1, 2);
    Ponto b(3, 4);

    const Ponto c(5, 6); // da para fazer get mas nao da para fazer set porque é const

    Ponto mat[3] = {a, b, c};

    cout << a.getAsString() << b.getAsString() << c.getAsString();
    cout << mat[0].getAsString() << mat[1].getAsString() << mat[2].getAsString();

    cout << "[V_1] Distancia entre A e B: " << a.distanceBetweenPoints(b.getX(), b.getY()) << endl;

    cout << "[V_2] Distancia entre A e B: " << a.distanceBetweenPoints(b) << endl;*/


    //EX 2
    Ponto a(1, 2);
    Ponto b(3, 4);
    Ponto c(5, 6);

    Ponto d(10, 10);

    Triangulo t1("t1", a, b, c);

    //cout << t1.getAsString();

    t1.setPontoA(d);
    cout << t1.getPontoA().getAsString() << endl;
    cout << a.getAsString() << endl;

    return 0;
}
