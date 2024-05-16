//
// Created by João Carvalho on 25/10/2023.
//

#include "Clube.h"

Clube::Clube(const string &nome, const string &descricao): nome(nome), descricao(descricao) {}

string Clube::getNome() const {return nome;}

string Clube::getDescricao() const {return descricao;}

void Clube::adicionaPessoa(Pessoa &pessoa) {
    pessoas.push_back(&pessoa);
}

bool Clube::verificaSocioVec(const int& bi) const{

    for(Pessoa* pessoa: pessoas){
        if(pessoa->getBi() == bi)
            return true;
    }

    return false;
}

bool Clube::removePessoa(const int &bi) {

    for(auto it = pessoas.begin(); it != pessoas.end(); ++it){
        if(bi == (*it)->getBi()){
            pessoas.erase(it);
            return true;
        }
    }

    return false;
}

string Clube::listaSocios() const{

    ostringstream oss;

    for(Pessoa* pessoa: pessoas){
        oss << pessoa->getDescricao() << endl;
    }
}
