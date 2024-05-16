#include <iostream>
#include "Clube.h"

using namespace std;

int main() {

    Pessoa a("Joao"), b("Gonzo");  // de acordo com o que estiver na classe Pessoa
    Clube c(50);
    c.setMembroDoClube(&a, 0);  // Pessoa a passa a pertencer ser membro do clube
    c.setMembroDoClube(&b, 1);  // Idem Pessoa b
    return 0;

    return 0;
}
