//
// Created by João Carvalho on 20/10/2022.
//

#include <iostream>
#include <string>
#include <sstream>
#include "televisao.h"

using namespace std;

int main(){
    Televisao a({"TVI", "SIC", "RTP1", "RTP2", "RTPAfrica"});
    a.liga();
    cout << a.mostraCanais();
    cout << a.obtemEstado();
    return 0;
}
