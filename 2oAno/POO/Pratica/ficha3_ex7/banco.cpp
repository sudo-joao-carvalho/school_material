//
// Created by João Carvalho on 28/10/2022.
//

#include "banco.h"

Banco::Banco(string nome):nome(nome) {}

void Banco::addConta(Pessoa *p) {
    Conta conta(p);

    for(auto & conta : contas){
        if(conta.obtemTitular().getNBI() == p->getNBI()){
            return ;
        }
    }
    
    contas.push_back(conta);
}

string Banco::obtemInfoConta(int BI){
    ostringstream oss;

    for(auto & conta: contas){
        if(conta.obtemTitular().getNBI() == BI){
            oss << conta.obtemConta() << endl;
        }
    }

    return oss.str();
}

void Banco::eliminaConta(int BI) {
    vector<Conta>::iterator it;

    for(it = contas.begin(); it != contas.end(); it++) {
        if (it->obtemTitular().getNBI() == BI) {
            contas.erase(it);
        } else
            it++;
    }
}
