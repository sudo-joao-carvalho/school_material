#include <iostream>
#include "Vetor.h"

int main() {
    Vetor v1(2.0, 1.0), v2(1.0, 3.0), v3(2.2), z;
    z = v1 + v2 + v3;
    cout << v1 << "+" << v2 << "+" << v3 << "=" << z << endl; // obs: "(x,y)" z = v1 + 10.0;
    cout << v1 << " + " << " 10 = " << z << endl;
    z = 20.0 + v1;
    cout << "20 + " << v1 << " = " << z << endl;
    z = v1 - v2;
    cout << v1 << " - " << v2 << " = " << z << endl;
    Vetor a(1.0, 1.0), b(2.0, 4.0);
    cout << " a= " << a << " b= " << b << endl;
    a += b += v1;
    a += b;
    a += 10.0;
    cout << " a= " << a << endl;
    cout << "(a == b)? " << (a == b) << endl;
    cout << "(a != b)? " << (a != b) << endl;
}
