#include <iostream>
#include <string>
#include <vector>
#include "Ponto.h"

using namespace std;

int main() {

    Ponto a(10, 20);
    Ponto b(3,2);
    Ponto matriz[3] = {(1,3), (2,4), (5,7)};
    cout << a.getAsString() << endl;
    cout << a.distancia(&b);

    return 0;
}
