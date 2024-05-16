//
// Created by João Carvalho on 08/11/2023.
//

#include "RegistoCivil.h"

//com o array podiamos fazer
/*RegistoCivil::RegistoCivil(const string &pais, initializer_list<Pessoa> pessoas):pais(pais), pessoas(p1, p2, p3) {

}*/

RegistoCivil::RegistoCivil(const string &pais):pais(pais) {

}

bool RegistoCivil::adicionaPessoa(const string& nome, const int& bi, const int& nif) {
    for(Pessoa pessoa: pessoas)
        if(pessoa.getBI() == bi)
            return false;

    pessoas.emplace_back(nome, bi, nif);
    return true;
}

bool RegistoCivil::apagaPessoa(const int &bi) {
    for(vector<Pessoa>::iterator it = pessoas.begin(); it != pessoas.end();){

        if((*it).getBI() == bi){
            pessoas.erase(it);
            return true;
        }else{
            ++it;
        }
    }

    return false;
}

void RegistoCivil::apagaPessoaEntreNumeros(const int &a, const int &b) {

    for(vector<Pessoa>::iterator it = pessoas.begin(); it!= pessoas.end();){

        if(a < (*it).getBI() < b){
            it = pessoas.erase(it);
        }else{
            ++it;
        }
    }
}

void RegistoCivil::apagaTodas() {pessoas.clear();}

string RegistoCivil::obtemNome(const int &bi) const{
    for(Pessoa pessoa: pessoas)
        if(pessoa.getBI() == bi)
            return pessoa.getNome();

    return "";
}

string RegistoCivil::getPais() const {return pais;}

int RegistoCivil::getNumPessoas() const {return pessoas.size();}