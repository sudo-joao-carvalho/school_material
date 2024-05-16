//
// Created by João Carvalho on 29/11/2023.
//

#ifndef F8_EX2_COLECAOCD_H
#define F8_EX2_COLECAOCD_H

#include "CD.h"

using namespace std;

class ColecaoCD {
public:
    ColecaoCD() = default;
    ColecaoCD(ColecaoCD& orig);

    bool adicionaCD(CD* novoCD);

    ColecaoCD operator=(ColecaoCD& outro);
private:
    vector<CD*> colecao; // e preciso um vetor porque nos queremos apontar para a classe base senao o polimorfismo nao funcionar, ou seja nao iamos conseguir redefinir as funcoes na classe base porque iamos ter uma copia da classe base
};

ostream& operator<<(ostream& COUT, const CD& cd){
    return COUT << cd.obtemInfo();
}


#endif //F8_EX2_COLECAOCD_H
