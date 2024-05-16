//
// Created by João Carvalho on 13/12/2023.
//

#ifndef F8_EX7_TARIFARIO_H
#define F8_EX7_TARIFARIO_H


#include "Cartao.h"

class Tarifario {

public:
    Tarifario();
    virtual ~Tarifario() = default;

    virtual bool autorizaChamada(const float& saldo) = 0;
    virtual float carregamento(const float& quantia) = 0;
    virtual float calculaCusto(const int& duracao) = 0;

    float getPrecoAPagar() const;
    void setPrecoAPagar(const float& precoAPagar);

    virtual string obtemNome() = 0;

private:
    Cartao* cartao;

};


#endif //F8_EX7_TARIFARIO_H
