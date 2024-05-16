//
// Created by João Carvalho on 03/11/2022.
//

#ifndef FICHA3_EX7_REGISTOCIVIL_H
#define FICHA3_EX7_REGISTOCIVIL_H

#include "pessoa.h"
using namespace std;

class RegistoCivil{
public:
    RegistoCivil() = default;
    RegistoCivil(const RegistoCivil& outro) = delete;

    //ADICAO
    void carregarPessoas(const string& filename);
    void exportarPessoas(const string& filename);
    void adicionarPessoa(const Pessoa& pessoa);

    //GETS
    Pessoa& obtemPessoa(const int& cc);
    string obtemPessoa() const;

private:
    vector<Pessoa> listOfPessoas;
    static const int limiteLeitura = 10;

};

#endif //FICHA3_EX7_REGISTOCIVIL_H
