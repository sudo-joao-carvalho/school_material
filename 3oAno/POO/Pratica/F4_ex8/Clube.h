//
// Created by João Carvalho on 25/10/2023.
//

#ifndef F4_EX8_CLUBE_H
#define F4_EX8_CLUBE_H

#include <string>
#include <vector>

#include "Pessoa.h"

using namespace std;

class Clube {
public:
    Clube(const string& nome, const string& descricao);

    string getNome() const;
    string getDescricao() const;

    void adicionaPessoa(Pessoa& pessoa);
    int verificaSocio(const int& bi) const; //retorna o indice em que a pessoa se encontra
    bool verificaSocioVec(const int& bi) const;
    bool removePessoa(const int& bi);

    string listaSocios() const;
private:

    string nome;
    string descricao;
    vector<Pessoa*> pessoas;
};


#endif //F4_EX8_CLUBE_H
