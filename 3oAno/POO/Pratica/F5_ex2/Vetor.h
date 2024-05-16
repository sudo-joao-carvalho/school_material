//
// Created by João Carvalho on 25/10/2023.
//

//operador membro obriga que o operando da esquerda seja do tipo da classe onde tem a redefinicao
//quando nao temos controlo sobre a classe do operando da esquerda temos que fazer um operador global

// operador [] tem que ser membro pq se o operando da esquerda for um elemento que esta no private de uma classe ele como é membro da classe consegue aceder ao private, se fosse global teria que haver um get daquele objeto e do indice

//CONSTRUTOR POR COPIA É O CONSTRUTOR QUE RECEBE UM OUTRO OBJETO DO MESMO TIPO
#ifndef F5_EX2_VETOR_H
#define F5_EX2_VETOR_H

#include <string>

using namespace std;

class Vetor {
public:
    Vetor() = default; //se nao meter o default tenho que o implementar no cpp, se meter default o compilador faz automaticamente
    Vetor(const double& db); // se tiver explicit atras, so o posso chamar de forma explicita tipo Vetor(0.0, 0.0) e se assim fosse nao poderia fazer aquilo do operador que transforma o double num vetor
    Vetor(const double& x, const double& y);

    double getX() const;
    double getY() const;

    void setX(const double& novoX);
    void setY(const double& novoY);

    string obtemCoordenadas() const;

    //Vetor operator+(Vetor b); //como existe o construto de receber so um double e para o caso em que o operador da esquerda é so um double fazemos a redefinicao do operador globalmente a receber 2 vetores e como existe aquele construtor é feita a conversao do double para vetor
    Vetor operator-(Vetor b); //este tambem deveria ser global como o mais mas nao é preciso para este exercicio
    Vetor operator+=(Vetor b);
    bool operator==(Vetor b);
    bool operator!=(Vetor b);

private:
    double x;
    double y;
};

ostream& operator<<(ostream& cout, Vetor v){
    cout << v.obtemCoordenadas();
    return cout;
}

Vetor operator+(Vetor v1, Vetor v2){

    Vetor aux;

    aux.setX(v1.getX() + v2.getX());
    aux.setY(v1.getY() + v2.getY());

    return aux;
}

#endif //F5_EX2_VETOR_H
