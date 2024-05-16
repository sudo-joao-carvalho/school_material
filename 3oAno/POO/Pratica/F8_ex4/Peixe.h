//
// Created by João Carvalho on 29/11/2023.
//

#ifndef F8_EX4_PEIXE_H
#define F8_EX4_PEIXE_H

#include <string>

using namespace std;

//uma classe sendo abstrata nao pode ser feito new Peixe() pois como tem um metodo virtual puro ele n vai ter implementaçao
// , entao como tem uma funcao que nao é implementada nao pode ser instanciada

class Peixe {
public:
    Peixe(const string& cor, const int& peso);
    //Peixe(Peixe& orig);
    virtual ~Peixe();

    virtual void alimenta(const int& quantidade) = 0;
    virtual string getEspecie() const = 0;
    string getCor() const;
    int getID() const;
    int getPeso() const;

    void setPeso(const int& quantidade);
    virtual string getPeixeAsString() const;

    //Peixe operator=(Peixe& outro);
    virtual Peixe* duplica() = 0;

private:
    static int contador;
    int nSerie;

    string cor;
    int peso;
};

ostream& operator<<(ostream& COUT, const Peixe& peixe){
    return COUT << peixe;
}


#endif //F8_EX4_PEIXE_H
