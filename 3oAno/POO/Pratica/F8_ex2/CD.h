//
// Created by João Carvalho on 29/11/2023.
//

#ifndef F8_EX2_CD_H
#define F8_EX2_CD_H

#include <string>

using namespace std;

class CD {
public:
    CD(const string& titulo);
    virtual bool acrescentaFaixa(const string& newFaixa) = 0;

    virtual string obtemInfo() const; //aqui nao esta abstrata pq todas as faixas tem coisas iguais, sendo assim tem implementacao no cpp e depois na redefinicao é so fazer CD::obtemInfo() e meter no oss em conjunto com as coisas da classe derivada

    virtual void play() = 0; //apresenta apenas no ecra o titulo do cd

private:
    string titulo;
    vector<string> faixas;

};


#endif //F8_EX2_CD_H
