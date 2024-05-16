//
// Created by João Carvalho on 08/11/2023.
//

#ifndef F6_EX1_PESSOA_H
#define F6_EX1_PESSOA_H

#include <string>
#include <sstream>

using namespace std;

class Pessoa {
public:
    Pessoa(string nome, int bi, int nif);
    string getNome() const;
    int getBI() const;
    int getNIF() const;
    void setNome(string nome);
    string descricao() const;
    // vai precisar de ser acrescentado por causa de RegCiv
    // Pessoa();
private:
    string nome;
    int bi, nif;
};


#endif //F6_EX1_PESSOA_H
