//
// Created by João Carvalho on 29/11/2023.
//

#include "Aquario.h"
#include <sstream>

void Aquario::adicionaPeixe(Peixe *novo) {
    peixes.push_back(novo->duplica());
}

string Aquario::getAquarioAsString() const {
    ostringstream oss;

    for(Peixe *peixe: peixes){
        oss << peixe->getPeixeAsString() << endl;
    }

    return oss.str();
}

void Aquario::eliminaPeixeById(const int &id) {
    for(vector<Peixe*>::iterator it = peixes.begin(); it != peixes.end();){
        if((*it)->getID() == id){
            delete *it;
            it = peixes.erase(it);
            return;
        }
        else{
            ++it;
        }
    }
}
