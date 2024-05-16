//
// Created by João Carvalho on 06/12/2023.
//

#ifndef F8_EX6_TARIFARIO_H
#define F8_EX6_TARIFARIO_H

using namespace std;

class Tarifario {
public:
    Tarifario();
    virtual ~Tarifario();

    void acrescentaTreino(const int& duracao);

    virtual int calculaPagamento() = 0; //calcula montante, apaga treinos e devolve valor a pagar

    int* getTempos() const;
    int getTamanho() const;

   // void setTempo(const int& posicao, const int& valor);
protected:
    void apagaTreinos();

private:
    int tamanho;
    int *duracaoTreinos; //array dinamico
    int capacidade;
};


#endif //F8_EX6_TARIFARIO_H
