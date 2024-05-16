//
// Created by João Carvalho on 08/11/2023.
//

#ifndef F6_EX1_REGISTOCIVIL_H
#define F6_EX1_REGISTOCIVIL_H

#include <string>
#include <vector>
#include "Pessoa.h"

using namespace std;

class RegistoCivil {

public:
    RegistoCivil(const string& pais);

    bool adicionaPessoa(const string& nome, const int& bi, const int& nif);
    bool apagaPessoa(const int& bi);
    void apagaPessoaEntreNumeros(const int& a, const int&b);
    void apagaTodas();

    string obtemNome(const int& bi) const;
    string getPais() const;
    int getNumPessoas() const;
private:
    const string pais;
    vector<Pessoa> pessoas;
};


#endif //F6_EX1_REGISTOCIVIL_H
